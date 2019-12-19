package com.example.listaitens;

import android.view.View;
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
    
    //Passando uma referencia da activity para o FormularioHelper, pode utilizar o findviewByid()
    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoQtdAtual = (EditText) activity.findViewById(R.id.formulario_qtdAtual);
        campoQtdNecessaria = (EditText) activity.findViewById(R.id.formulario_qtdNecessaria);
    }

    public Produto pegaProduto() {
        //Instanciando um novo Produto
        Produto produto = new Produto();
        //Setando o nome com o valor obtido da variavel campoNome
        produto.setNome(campoNome.getText().toString());
        produto.setQtdAtual(campoQtdAtual.getText().toString());
        produto.setQtdNecessaria(campoQtdNecessaria.getText().toString());

        return produto;
    }
}
