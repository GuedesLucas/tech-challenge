package br.com.fiap.techchallenge.core.model;

import br.com.fiap.techchallenge.core.Enum.StatusPedidoEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    private BigDecimal valorTotal;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Pedido() {
    }

    public Pedido(Cliente cliente, BigDecimal valorTotal) {
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = StatusPedidoEnum.RECEBIDO;
        this.createAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
