package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CadastrarPedidoRequestDTO(
        Long idCliente,
        List<CadastrarItemsPedidoRequestDTO> items,
        BigDecimal valorTotal

) {
}
