package br.com.fiap.techchallenge.core.DTO;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class CadastrarClienteRequestDTO {

    @NotBlank(message = "nome: Campo obigatório.")
    private String nome;

    @CPF(message = "cpf: Campo inválido")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
