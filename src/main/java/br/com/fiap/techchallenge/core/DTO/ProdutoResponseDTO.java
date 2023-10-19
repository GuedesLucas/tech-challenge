package br.com.fiap.techchallenge.core.DTO;

import br.com.fiap.techchallenge.core.Enum.CategoriaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoResponseDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String descricao;
    @JsonProperty
    private BigDecimal preco;
    @JsonProperty
    private String categoria;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
