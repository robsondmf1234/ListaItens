package com.example.listaitens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listaitens.dao.ProdutoDAO;
import com.example.listaitens.modelo.Produto;

import java.util.List;

public class ListaItens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
        
        //Instanciando o produto DAO
        ProdutoDAO dao = new ProdutoDAO(this);
        //Declarando uma lista para poder receber os produtos buscado no banco de dados
        List<Produto> produtos = dao.buscaProdutos();
        //Fechando a conexao com o banco
        dao.close();
        
        //Recuperando a referencia do xml 
        ListView listaItens = findViewById(R.id.lista_itens);
        //Convertendo a lista de String para view com o adapter (referencia da activity,layout para mostrar os dados, fonte dos dados)
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);
        //Setando o adapter na lista 
        listaItens.setAdapter(adapter);
        
        //Recuperando a referencia do botao
        Button botaoVaiPraSegunda = findViewById(R.id.btn_segundaActivity);
        //Implementando um Listener no botao 
        botaoVaiPraSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProFormulario = new Intent(ListaItens.this, FormularioActivity.class);
                startActivity(vaiProFormulario);
            }
        });
    }
}
