package br.com.fiap.techchallenge.core.model;

import java.time.LocalDateTime;

import br.com.fiap.techchallenge.core.Enum.StatusPagamentoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum status;

    @OneToOne
    private GatewayPagamento gateway;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Pagamento() {

    }

    public Pagamento(Pedido pedido, GatewayPagamento gateway) {
        this.pedido = pedido;
        this.status = StatusPagamentoEnum.ABERTO;
        this.gateway = gateway;
        this.createAt = LocalDateTime.now();
    }
    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Pedido return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * @return StatusPaymentEnum return the status
     */
    public StatusPagamentoEnum getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(StatusPagamentoEnum status) {
        this.status = status;
    }

    /**
     * @return LocalDateTime return the createAt
     */
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt the createAt to set
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    /**
     * @return LocalDateTime return the updateAt
     */
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt the updateAt to set
     */
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
