package br.com.fiap.techchallenge.core.DTO;

import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponseDTO(
        Long id,
        Long idCliente,
        List<ItemsPedidoResponseDTO> items,
        BigDecimal valorTotal,
        StatusPedidoEnum status
) {
}
