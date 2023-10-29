package br.com.fiap.techchallenge.core.DTO;

import java.math.BigDecimal;

import br.com.fiap.techchallenge.core.model.Pedido;

public record GerarAPIPagamentoDTO(Long orderID, BigDecimal amount) {
    public static GerarAPIPagamentoDTO fromPedido(Pedido pedido) {
        return new GerarAPIPagamentoDTO(pedido.getId(), pedido.getValorTotal());
    }
}
