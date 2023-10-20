package br.com.fiap.techchallenge.core.model;

import java.io.Serializable;

public class ItemPedidoId implements Serializable {

    private Long pedido;
    private Long produto;

    public ItemPedidoId() {
    }

    public ItemPedidoId(Long pedido, Long produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }
}
