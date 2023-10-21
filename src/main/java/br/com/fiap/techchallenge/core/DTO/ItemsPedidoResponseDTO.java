package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;

public record ItemsPedidoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidade
) {
}
