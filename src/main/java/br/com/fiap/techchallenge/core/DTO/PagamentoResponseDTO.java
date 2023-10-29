package br.com.fiap.techchallenge.core.DTO;

import java.util.UUID;

public record PagamentoResponseDTO(UUID paymentHash) {
    public static PagamentoResponseDTO criarPagamentoResponseDTO(UUID hash) {
        return new PagamentoResponseDTO(hash);
    }
}
