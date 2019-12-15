package com.example.listaitens.modelo;

/**
 * Created by Robson on 10/12/2019
 */
public class Produto {

    private Long id;
    private String nome;
    private String qtdAtual;
    private String qtdNecessaria;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getQtdAtual() {
        return qtdAtual;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQtdAtual(String qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public void setQtdNecessaria(String qtdNecessaria) {
        this.qtdNecessaria = qtdNecessaria;
    }

    public String getQtdNecessaria() {
        return qtdNecessaria;
    }


}
