package com.josiana.estoqueveiculos.model;

import jakarta.persistence.Entity;

@Entity
public class Marca extends EntidadeBase {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}