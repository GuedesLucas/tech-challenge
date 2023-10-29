package br.com.fiap.techchallenge.core.DTO;

public class PagamentoRequestDTO {
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
