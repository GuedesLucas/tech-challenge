package br.com.fiap.techchallenge.core.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.fiap.techchallenge.core.DTO.GatewayPagamentoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.PagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PagamentoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPagamentoEnum;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.model.GatewayPagamento;
import br.com.fiap.techchallenge.core.model.Pagamento;
import br.com.fiap.techchallenge.core.model.Pedido;
import br.com.fiap.techchallenge.infrastructure.api.exception.APICallException;
import br.com.fiap.techchallenge.infrastructure.repositories.PagamentoRepository;

@Service
public class PagamentoService {
    @Value("${api.base-url}")
    private String apiUrl;

    private final PagamentoRepository pagamentoRepository;
    private final GatewayPagamentoService gatewayPagamentoService;
    private final PedidoService pedidoService;

    public PagamentoService(PagamentoRepository pagamentoRepository, GatewayPagamentoService gatewayPagamentoService, PedidoService pedidoService) {
        this.pagamentoRepository = pagamentoRepository;
        this.gatewayPagamentoService = gatewayPagamentoService;
        this.pedidoService = pedidoService;
    }

    public PagamentoResponseDTO cadastrarPagamento(final PagamentoRequestDTO pagamentoRequestDTO) throws APICallException{
        Pedido pedido = this.pedidoService.buscarPedidoPorId(pagamentoRequestDTO.getPedidoId());
        PagamentoResponseDTO pagamentoAPI = this.realizarSolicitacaoPagamentoAPI(pedido);
        
        return validaPagamentoResponseAPI(pedido, pagamentoAPI);
    }

    private Pagamento validatePagamento(Pedido pedido, PagamentoResponseDTO pagamentoAPI) {
        return this.pagamentoRepository.findByPedidoAndGatewayAndStatus(pedido, pagamentoAPI.paymentHash(),StatusPagamentoEnum.CONCLUIDO);

    }

    private PagamentoResponseDTO realizarSolicitacaoPagamentoAPI(Pedido pedido) throws APICallException{
        try {
            // GerarAPIPagamentoDTO pagamentoAPI = GerarAPIPagamentoDTO.fromPedido(pedido);
            // GerarAPIPagamentoResponseDTO paymentAPI = restApi.post(this.construirUrlDaAPI("/payment/qrcode"), pagamentoAPI,GerarAPIPagamentoResponseDTO.class);

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            return PagamentoResponseDTO.criarPagamentoResponseDTO(uuidAsString);
        } catch (APICallException apiCallException) {
            throw apiCallException; 
        }
    }

    public String construirUrlDaAPI(String rota) {
        return apiUrl + rota;
    }

    private PagamentoResponseDTO validaPagamentoResponseAPI(Pedido pedido, PagamentoResponseDTO pagamentoAPI) {
        Pagamento pagamento = validatePagamento(pedido, pagamentoAPI);
        if (pagamento != null) {
            return pagamentoAPI;
        }
        GatewayPagamento gp = this.gatewayPagamentoService.salvarGatewayPagamento(pagamentoAPI.paymentHash());

        final var pg = new Pagamento(pedido, gp);

        this.pagamentoRepository.save(pg);
        return pagamentoAPI;
    }

    public RealizaPagamentoResponseDTO realizarPagamento(RealizaPagamentoRequestDTO realizaPagamentoRequesDTO) throws APICallException{
        return this.realizarPagamentoAPI(realizaPagamentoRequesDTO);
    }

    private RealizaPagamentoResponseDTO realizarPagamentoAPI(RealizaPagamentoRequestDTO realizaPagamentoRequesDTO){
        try {
            RealizaPagamentoResponseDTO pg = new RealizaPagamentoResponseDTO();
            pg.setStatus("success");
            return pg;
        } catch (APICallException apiCallException) {
            throw apiCallException; 
        }
    }

    public RealizaPagamentoResponseDTO webhookPagamento(String gatewayPamentoId) {
        Pagamento pagamento = pagamentoRepository.findByGatewayAndStatus(gatewayPamentoId, StatusPagamentoEnum.ABERTO);
    
        if (pagamento == null) return montaRetornoWook("FAIL");
        GatewayPagamentoResponseDTO gatewayStatus = realizarBuscaPagamentoAPI(pagamento);
        if (!gatewayStatus.status().contains("success")) return montaRetornoWook("FAIL");
            
        pagamento.setStatus(StatusPagamentoEnum.CONCLUIDO);
        pagamento.setUpdateAt(LocalDateTime.now());
        pagamentoRepository.save(pagamento);
        this.pedidoService.atualizarStatusPedido(pagamento.getPedido().getId(), StatusPedidoEnum.EM_PREPARACAO);
        return montaRetornoWook("CONCLUIDO");
    }

    private GatewayPagamentoResponseDTO realizarBuscaPagamentoAPI(Pagamento pagamento) throws APICallException{
        try {
            return GatewayPagamentoResponseDTO.fromPagamento(pagamento);
        } catch (APICallException apiCallException) {
            throw apiCallException; 
        }
    }

    private RealizaPagamentoResponseDTO montaRetornoWook(String status) {
        RealizaPagamentoResponseDTO realizado = new RealizaPagamentoResponseDTO();
        realizado.setStatus(status);
        return realizado;
    }
}
