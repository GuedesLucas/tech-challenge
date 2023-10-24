package br.com.fiap.techchallenge.core.services;

import br.com.fiap.techchallenge.core.DTO.CadastrarPedidoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ItemsPedidoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.PedidoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.exception.PedidoNaoEncontratoException;
import br.com.fiap.techchallenge.core.model.Cliente;
import br.com.fiap.techchallenge.core.model.ItemPedido;
import br.com.fiap.techchallenge.core.model.Pedido;
import br.com.fiap.techchallenge.infrastructure.ItemPedidoRepository;
import br.com.fiap.techchallenge.infrastructure.PedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoRepository itemPedidoRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public PedidoResponseDTO cadastrarPedido(final CadastrarPedidoRequestDTO pedidoRequestDTO) {
        Cliente cliente = null;
        if (pedidoRequestDTO.idCliente() != null) {
            cliente = clienteService.buscarClientePorId(pedidoRequestDTO.idCliente());
        }

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemPedido> itemsPedido = new ArrayList<>();

        for (final var itemRequest : pedidoRequestDTO.items()) {
            final var produto = produtoService.buscarProdutoPorId(itemRequest.idProduto().toString());
            itemsPedido.add(new ItemPedido(null, produto, itemRequest.quantidade()));
            valorTotal = valorTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(itemRequest.quantidade())));
        }

        final var pedido = new Pedido(cliente, valorTotal);
        pedidoRepository.save(pedido);

        itemsPedido.forEach(itemPedido -> itemPedido.setPedido(pedido));
        itemPedidoRepository.saveAll(itemsPedido);

        pedido.setItems(itemsPedido);
        return pedidoToPedidoResponse(pedido);
    }

    public void atualizarStatusPedido(final Long idPedido, final StatusPedidoEnum status) {
        final var pedido = buscarPedidoPorId(idPedido);
        pedido.setStatus(status);

        pedidoRepository.save(pedido);
    }

    public List<PedidoResponseDTO> listarPedidos(StatusPedidoEnum filtroStatus) {
        if (filtroStatus != null) {
            return pedidoRepository.findAllByStatus(filtroStatus).stream().map(PedidoService::pedidoToPedidoResponse).toList();
        }
        return pedidoRepository.findAll().stream()
                .map(PedidoService::pedidoToPedidoResponse)
                .sorted(Comparator.comparingInt(p -> p.status().getOrdem()))
                .toList();
    }

    public PedidoResponseDTO buscarPedido(Long idPedido) {
        return pedidoToPedidoResponse(buscarPedidoPorId(idPedido));
    }


    private Pedido buscarPedidoPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido).orElseThrow(PedidoNaoEncontratoException::new);
    }

    private static PedidoResponseDTO pedidoToPedidoResponse(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente() == null ? null : pedido.getCliente().getId(),
                itemsPedidoToResponse(pedido.getItems()),
                pedido.getValorTotal(),
                pedido.getStatus()
        );
    }

    private static List<ItemsPedidoResponseDTO> itemsPedidoToResponse(List<ItemPedido> itemsPedido) {
        return itemsPedido.stream().map(itemPedido -> new ItemsPedidoResponseDTO(
                itemPedido.getProduto().getId(),
                itemPedido.getProduto().getNome(),
                itemPedido.getProduto().getPreco(),
                itemPedido.getQuantidade()
        )).toList();
    }
}
