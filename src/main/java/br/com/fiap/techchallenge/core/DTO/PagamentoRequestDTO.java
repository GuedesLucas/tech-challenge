package br.com.fiap.techchallenge.core.DTO;

import jakarta.validation.constraints.NotBlank;

public class PagamentoRequestDTO {
    @NotBlank(message = "pedidoId: Campo obigatório.")
    private Long pedidoId;

    /**
     * @return Long return the pedidoId
     */
    public Long getPedidoId() {
        return pedidoId;
    }

    /**
     * @param pedidoId the pedidoId to set
     */
    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
}
