package br.com.fiap.techchallenge.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long cpf;
    private LocalDateTime createDate;

    public Cliente() {
    }

    public Cliente(String nome, Long cpf, LocalDateTime createDate) {
        this.nome = nome;
        this.cpf = cpf;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
