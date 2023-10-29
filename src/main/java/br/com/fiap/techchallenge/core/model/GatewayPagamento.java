package br.com.fiap.techchallenge.core.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GatewayPagamento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String gatewayPagamentoID;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public GatewayPagamento() {

    }

    public GatewayPagamento(String gatewayPagamentoID) {
        this.gatewayPagamentoID = gatewayPagamentoID;
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
     * @return String return the gatewayPagamentoID
     */
    public String getGatewayPagamentoID() {
        return gatewayPagamentoID;
    }

    /**
     * @param gatewayPagamentoID the gatewayPagamentoID to set
     */
    public void setGatewayPagamentoID(String gatewayPagamentoID) {
        this.gatewayPagamentoID = gatewayPagamentoID;
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
