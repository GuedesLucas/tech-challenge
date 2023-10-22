package br.com.fiap.techchallenge.core.ports.controller;

import br.com.fiap.techchallenge.core.DTO.CadastrarPedidoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PedidoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public interface PedidoController {

    @Operation(
            summary = "Cadastra um novo pedido.",
            description = "Cadastra um novo pedido.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(final CadastrarPedidoRequestDTO cadastrarPedidoRequestDTO);

    @ApiResponses({
            @ApiResponse(responseCode = "202", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<Void> atualizarStatusPedido(final Long id, final StatusPedidoEnum status);

    @Operation(
            summary = "Busca um pedido.",
            description = "Busca um pedido por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<PedidoResponseDTO> buscarPedido(Long id);

    @Operation(
            summary = "Listar pedidos.",
            description = "Listar todos pedidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(final StatusPedidoEnum filtroStatus);
}
