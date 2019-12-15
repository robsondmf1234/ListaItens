package com.example.listaitens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ListaItens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);

        String[] alunos = {"Arroz", "Macarrão", "Café", "Açucar"};
        ListView listaItens = findViewById(R.id.lista_itens);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        listaItens.setAdapter(adapter);

        Button botaoVaiPraSegunda = findViewById(R.id.btn_segundaActivity);
        botaoVaiPraSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProFormulario = new Intent(ListaItens.this,FormularioActivity.class);
                startActivity(vaiProFormulario);
            }
        });
    }
}
