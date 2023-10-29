package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record GatewayPagamentoResponseDTO(
    UUID id,
    Long orderId,
    BigDecimal amount,
    String paymentTime,
    String status
) {
    
}
