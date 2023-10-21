package br.com.fiap.techchallenge.application;


import br.com.fiap.techchallenge.core.DTO.CadastrarPedidoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PedidoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(
            summary = "Cadastra um novo pedido.",
            description = "Cadastra um novo pedido.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(@Valid @RequestBody CadastrarPedidoRequestDTO cadastrarPedidoRequestDTO){
        final var pedidoResponseDTO = pedidoService.cadastrarPedido(cadastrarPedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDTO);
    }

    @Operation(
            summary = "Altera status pedido.",
            description = "Altera status de um pedido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarStatusPedido(@PathVariable("id") Long id, final @RequestParam StatusPedidoEnum status){
        pedidoService.atualizarStatusPedido(id, status);
        return ResponseEntity.accepted().build();
    }

    @Operation(
            summary = "Busca um pedido.",
            description = "Busca um pedido por id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PedidoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedido(@PathVariable("id") Long id){
        final var pedidoResponseDTO = pedidoService.buscarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTO);
    }

    @Operation(
            summary = "Listar pedidos.",
            description = "Listar todos pedidos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(@RequestParam(required = false) StatusPedidoEnum filtroStatus){
        List<PedidoResponseDTO> pedidoResponseDTOList = pedidoService.listarPedidos(filtroStatus);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTOList);
    }
}
