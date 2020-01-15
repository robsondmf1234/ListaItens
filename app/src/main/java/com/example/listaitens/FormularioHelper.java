package com.example.listaitens;

import android.widget.EditText;

import com.example.listaitens.modelo.Produto;

/**
 * Created by Robson on 10/12/2019
 */
public class FormularioHelper {
    
    //Atributos da classe
    private final EditText campoNome;
    private final EditText campoQtdAtual;
    private final EditText campoQtdNecessaria;
    private final EditText campoTelefone;
    private final EditText campoSite;

    private Produto produto;
    
    //Passando uma referencia da activity para o FormularioHelper, pode utilizar o findviewByid()
    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoQtdAtual = (EditText) activity.findViewById(R.id.formulario_qtdAtual);
        campoQtdNecessaria = (EditText) activity.findViewById(R.id.formulario_qtdNecessaria);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);

        //Instanciando um novo Produto
        produto = new Produto();
    }

    public Produto pegaProduto() {

        //Setando o nome com o valor obtido da variavel campoNome
        produto.setNome(campoNome.getText().toString());
        produto.setQtdAtual(campoQtdAtual.getText().toString());
        produto.setQtdNecessaria(campoQtdNecessaria.getText().toString());
        produto.setTelefone(campoTelefone.getText().toString());
        produto.setSite(campoSite.getText().toString());

        //retonando o objeto produto
        return produto;
    }

    public void preencheFormulario(Produto produto) {
        campoNome.setText(produto.getNome());
        campoQtdAtual.setText(produto.getQtdAtual());
        campoQtdNecessaria.setText(produto.getQtdNecessaria());
        campoTelefone.setText(produto.getTelefone());
        campoSite.setText(produto.getSite());

        this.produto = produto;
    }
}
