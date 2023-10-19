package br.com.fiap.techchallenge.core.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class CadastrarProdutoRequestDTO {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String descricao;

    @Pattern(regexp = "\\d{1,}\\.\\d{2}")
    private String preco;

    @Pattern(regexp = "LANCHE|SOBREMESA|BEBIDA|ACOMPANHAMENTO")
    private String categoria;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }
}
