package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.techchallenge.core.model.Pagamento;

public record GatewayPagamentoResponseDTO(
        String id,
        Long orderId,
        BigDecimal amount,
        String paymentTime,
        String status
) {
    public static GatewayPagamentoResponseDTO fromPagamento(Pagamento pagamento) {
        return new GatewayPagamentoResponseDTO(
                pagamento.getGateway().getGatewayPagamentoID(),
                pagamento.getPedido().getId(),
                pagamento.getPedido().getValorTotal(),
                LocalDateTime.now().toString(), // Assume que o valor do paymentTime é uma string
                "success"
        );
    }
}