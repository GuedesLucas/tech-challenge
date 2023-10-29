package br.com.fiap.techchallenge.application.adapters.controller;

import br.com.fiap.techchallenge.core.DTO.PagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PagamentoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoResponseDTO;
import br.com.fiap.techchallenge.core.ports.controller.PagamentoController;
import br.com.fiap.techchallenge.core.services.PagamentoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamentos", description = "Gerenciamento de Pagamentos")
public class PagamentoControllerImpl implements PagamentoController {

    private PagamentoService pagamentoService;

    public PagamentoControllerImpl(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/solicitar")
    public ResponseEntity<PagamentoResponseDTO> cadastrarPagamento(
            @Valid
            @RequestBody
            final PagamentoRequestDTO requestDTO) {
        final var responseDTO = pagamentoService.cadastrarPagamento(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/pagar")
    public ResponseEntity<RealizaPagamentoResponseDTO> realizarPagamento(
            @Valid
            @RequestBody
            final RealizaPagamentoRequestDTO requestDTO) {
        final var responseDTO = pagamentoService.realizarPagamento(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @GetMapping("/webhook/:id")
    public ResponseEntity<RealizaPagamentoResponseDTO> webhookPagamento(
            @PathVariable("id") 
            final String id) {
        final var responseDTO = pagamentoService.webhookPagamento(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
