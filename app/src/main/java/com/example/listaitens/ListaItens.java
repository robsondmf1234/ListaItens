package com.example.listaitens;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listaitens.dao.ProdutoDAO;
import com.example.listaitens.modelo.Produto;

import java.util.List;

public class ListaItens extends AppCompatActivity {

    private ListView listaItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        //Recuperando a referencia do xml
        listaItens = findViewById(R.id.lista_itens);
        
        //Método para carregar a lista de Produtos
        carregaLista();

        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View itemClicado, int position, long id) {
                Produto produto = (Produto) listaItens.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaItens.this,FormularioActivity.class);
                
                //Pendurando o produto e passando ele junto com a Intent
                intentVaiProFormulario.putExtra("produto",produto);

                startActivity(intentVaiProFormulario);
            }
        });

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

        registerForContextMenu(listaItens);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    private void carregaLista() {
        //Instanciando o produto DAO
        ProdutoDAO dao = new ProdutoDAO(this);
        //Declarando uma lista para poder receber os produtos buscado no banco de dados
        List<Produto> produtos = dao.buscaProdutos();
        //Fechando a conexao com o banco
        dao.close();

        //Convertendo a lista de String para view com o adapter (referencia da activity,layout para mostrar os dados, fonte dos dados)
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);
        //Setando o adapter na lista
        listaItens.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto produto = (Produto) listaItens.getItemAtPosition(info.position);
                ProdutoDAO dao = new ProdutoDAO(ListaItens.this);
                dao.delete(produto);
                dao.close();
                carregaLista();
                return false;
            }
        });
    }

}