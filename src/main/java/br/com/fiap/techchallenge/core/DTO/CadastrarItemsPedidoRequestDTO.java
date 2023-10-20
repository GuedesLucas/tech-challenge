package br.com.fiap.techchallenge.core.DTO;

public record CadastrarItemsPedidoRequestDTO(
        Long idProduto,
        Integer quantidade
) {
}
