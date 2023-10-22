package br.com.fiap.techchallenge.application.adapters.controller;


import br.com.fiap.techchallenge.core.DTO.CadastrarPedidoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PedidoResponseDTO;
import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import br.com.fiap.techchallenge.core.ports.controller.PedidoController;
import br.com.fiap.techchallenge.core.services.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class PedidoControllerImpl implements PedidoController {

    private PedidoService pedidoService;

    public PedidoControllerImpl(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrarPedido(
            @Valid
            @RequestBody
            final CadastrarPedidoRequestDTO cadastrarPedidoRequestDTO) {
        final var pedidoResponseDTO = pedidoService.cadastrarPedido(cadastrarPedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> atualizarStatusPedido(
            @PathVariable("id")
            final Long id,
            @RequestParam
            final StatusPedidoEnum status) {
        pedidoService.atualizarStatusPedido(id, status);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPedido(@PathVariable("id") final Long id) {
        final var pedidoResponseDTO = pedidoService.buscarPedido(id);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(
            @RequestParam(required = false)
            final StatusPedidoEnum filtroStatus) {
        final var pedidoResponseDTOList = pedidoService.listarPedidos(filtroStatus);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponseDTOList);
    }
}
