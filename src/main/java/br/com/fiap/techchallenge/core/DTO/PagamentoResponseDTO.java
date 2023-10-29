package br.com.fiap.techchallenge.core.DTO;

public record PagamentoResponseDTO(String paymentHash) {
    public static PagamentoResponseDTO criarPagamentoResponseDTO(String hash) {
        return new PagamentoResponseDTO(hash);
    }
}
