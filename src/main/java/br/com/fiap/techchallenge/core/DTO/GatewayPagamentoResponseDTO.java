package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GatewayPagamentoResponseDTO(
    String id,
    @JsonProperty("order_id")
    Long orderId,
    BigDecimal amount,
    @JsonProperty("payment_time")
    String paymentTime,
    String status
) {
    
}
