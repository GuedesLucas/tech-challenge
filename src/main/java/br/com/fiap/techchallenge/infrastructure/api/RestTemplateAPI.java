package br.com.fiap.techchallenge.infrastructure.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.techchallenge.infrastructure.api.exception.APICallException;

@Component
public class RestTemplateAPI {
    private final RestTemplate restTemplate;

    public RestTemplateAPI() {
        this.restTemplate = new RestTemplate();
    }

    public <T> T execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType) {
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, method, requestEntity, responseType);
            return responseEntity.getBody();
        } catch (APICallException exception) {
            this.ExceptionApi(exception);
            throw exception;
        }
    }

    public <T> T get(String url, Class<T> responseType) {
        return execute(url, HttpMethod.GET, null, responseType);
    }

    public <T> T post(String url, Object requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return execute(url, HttpMethod.POST, requestEntity, responseType);
    }

    public <T> T put(String url, Object requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return execute(url, HttpMethod.PUT, requestEntity, responseType);
    }

    public <T> T patch(String url, Object requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return execute(url, HttpMethod.PATCH, requestEntity, responseType);
    }

    public <T> T delete(String url, Class<T> responseType) {
        return execute(url, HttpMethod.DELETE, null, responseType);
    }

    private void ExceptionApi(APICallException exception) {
        String responseBody = exception.getResponseBody();
        HttpStatus statusCode = exception.getStatusCode();
        throw new APICallException(statusCode, responseBody);
    }
}
