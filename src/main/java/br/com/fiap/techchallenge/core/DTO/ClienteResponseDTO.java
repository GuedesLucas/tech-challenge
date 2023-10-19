package br.com.fiap.techchallenge.core.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ClienteResponseDTO {

    @JsonProperty
    private Long id;

    @JsonProperty
    private String nome;

    @JsonProperty
    private Long cpf;

    public ClienteResponseDTO(Long id, String nome, Long cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }
}
