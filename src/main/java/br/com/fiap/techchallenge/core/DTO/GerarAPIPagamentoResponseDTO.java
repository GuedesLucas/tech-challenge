package br.com.fiap.techchallenge.core.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GerarAPIPagamentoResponseDTO {
    @JsonProperty("payment_id")
    private String paymentId;

    /**
     * @return String return the paymentId
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}
