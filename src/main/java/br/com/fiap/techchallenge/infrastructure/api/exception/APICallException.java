package br.com.fiap.techchallenge.infrastructure.api.exception;

import org.springframework.http.HttpStatus;

public class APICallException extends RuntimeException {
    private HttpStatus statusCode;
    private String responseBody;

    public APICallException(HttpStatus statusCode, String responseBody) {
        super("Erro ao chamar a API: " + statusCode + " - " + responseBody);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}