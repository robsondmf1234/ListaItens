package com.example.listaitens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listaitens.dao.ProdutoDAO;
import com.example.listaitens.modelo.Produto;

public class FormularioActivity extends AppCompatActivity {

    //Declarando uma classe para auxiliar na coleta dos dados dos campos 
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        //Instanciando objeto da classe FormularioHelper
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");

        if (produto != null) {
            helper.preencheFormulario(produto);
        }

    }

    //Option menu é o menu que fica na parte superior direita da tela 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Devolvendo uma instancia de um menu inflater(utilizado para transformar uma xml em uma view)
        MenuInflater inflater = getMenuInflater();
        //Inflando o menu_formulario(xml) ,para uma view e inserindo na tela 
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Swicth case para identificar o id da opção clicada no menuOptionsMenu
        switch (item.getItemId()) {
            //caso o o id da opção clicada seja menu_formulario_ok 
            case R.id.menu_formulario_ok:
                //Instanciado o objeto do formulariohelper
                Produto produto = helper.pegaProduto();

                //Instanciado o objeto do produto dao
                ProdutoDAO dao = new ProdutoDAO(this);

                if (produto.getId() != null) {
                    dao.altera(produto);
                } else {
                    //chamando o método inserir da Classe ProdutoDAO
                    dao.insert(produto);
                }

                //fechando a conexao com o banco
                dao.close();
                Toast.makeText(FormularioActivity.this, "Produto " + produto.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
