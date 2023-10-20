package br.com.fiap.techchallenge.core.services;

import br.com.fiap.techchallenge.core.DTO.CadastrarPedidoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.ItemsPedidoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.PedidoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.exception.PedidoNaoEncontratoException;
import br.com.fiap.techchallenge.core.model.ItemPedido;
import br.com.fiap.techchallenge.core.model.Pedido;
import br.com.fiap.techchallenge.infrastructure.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository repository, ClienteService clienteService, ProdutoService produtoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }

    public PedidoResponseDTO cadastrarPedido(final CadastrarPedidoRequestDTO pedidoRequestDTO) {
        final var cliente = clienteService.buscarClientePorId(pedidoRequestDTO.idCliente());

        final var itemsPedido = pedidoRequestDTO.items().stream()
                .map(itemRequest -> {
                    final var produto = produtoService.buscarProdutoPorId(itemRequest.idProduto().toString());
                    return new ItemPedido(null, produto, itemRequest.quantidade());
                }).toList();


        final var pedido = new Pedido(cliente, pedidoRequestDTO.valorTotal());
        repository.save(pedido);

        itemsPedido.forEach(itemPedido -> itemPedido.setPedido(pedido));

        return pedidoToPedidoResponse(pedido);
    }

    public void atualizarStatusPedido(final Long idPedido, final String status) {
        final var pedido = buscarPedidoPorId(idPedido);
        pedido.setStatus(StatusPedidoEnum.valueOf(status));

        repository.save(pedido);
    }

    private Pedido buscarPedidoPorId(Long idPedido) {
        return repository.findById(idPedido).orElseThrow(PedidoNaoEncontratoException::new);
    }

    private static PedidoResponseDTO pedidoToPedidoResponse(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getCliente().getId(),
                itemsPedidoToResponse(pedido.getItems()),
                pedido.getValorTotal(),
                pedido.getStatus()
        );
    }

    private static List<ItemsPedidoResponseDTO> itemsPedidoToResponse(List<ItemPedido> itemsPedido) {
        return itemsPedido.stream().map(itemPedido -> new ItemsPedidoResponseDTO(
                itemPedido.getProduto().getId(),
                itemPedido.getQuantidade()
        )).toList();
    }
}
