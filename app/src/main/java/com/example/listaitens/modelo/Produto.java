package com.example.listaitens.modelo;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Robson on 10/12/2019
 */
public class Produto implements Serializable {

    //Atributos da Classe
    private Long id;
    private String nome;
    private String qtdAtual;
    private String qtdNecessaria;

    //Métodos gets e sets
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

    //Sobrescrevendo o método to String para uma melhor visualização na tela do Aplictaivo
    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
