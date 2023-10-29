package br.com.fiap.techchallenge.core.DTO;

import java.util.UUID;

public class GerarAPIPagamentoResponseDTO {
    private UUID paymentId;

    /**
     * @return UUID return the paymentId
     */
    public UUID getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

}
