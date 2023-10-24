package br.com.fiap.techchallenge.core.DTO;

import java.util.List;

public record CadastrarPedidoRequestDTO(
        Long idCliente,
        List<CadastrarItemsPedidoRequestDTO> items

) {
}
