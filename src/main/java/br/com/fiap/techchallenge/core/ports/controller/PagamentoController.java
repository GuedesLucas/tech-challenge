package br.com.fiap.techchallenge.core.ports.controller;

import org.springframework.http.ResponseEntity;

import br.com.fiap.techchallenge.core.DTO.PagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.PagamentoResponseDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoRequestDTO;
import br.com.fiap.techchallenge.core.DTO.RealizaPagamentoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Pagamentos", description = "Gerenciamento de pagamento")
public interface PagamentoController {
    @Operation(
        summary = "Cadastra um pagamento pedido.",
        description = "Solicia pagamento do pedido para a processadora de pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PagamentoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<PagamentoResponseDTO> cadastrarPagamento(PagamentoRequestDTO pagamentoRequesDTO);
    
    @Operation(
        summary = "Realiza o pagamento pedido.",
        description = "Realiza o pagamento do pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RealizaPagamentoResponseDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema()) }) })
    public ResponseEntity<RealizaPagamentoResponseDTO> realizarPagamento(RealizaPagamentoRequestDTO realizaPagamentoRequesDTO);
     

    @Operation(
        summary = "Webhook Pagamento.",
        description = "Permite processadora avisar que o pagamento foi processado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PagamentoResponseDTO.class), mediaType = "application/json") })
    })
    public ResponseEntity<RealizaPagamentoResponseDTO> webhookPagamento(String gatewayPamentoId);
}
