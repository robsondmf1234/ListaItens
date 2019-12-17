package com.example.listaitens.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.listaitens.modelo.Produto;

/**
 * Created by Robson on 15/12/2019
 */
public class ProdutoDAO extends SQLiteOpenHelper {

    public ProdutoDAO(@Nullable Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, qtdAtual TEXT, qtdNecessaria TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Produtos";
        db.execSQL(sql);
        onCreate(db);
    }

    //Inserindo valores no Banco
    public void insert(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", produto.getNome());
        dados.put("qtdAtual", produto.getQtdAtual());
        dados.put("qtdNecessaria", produto.getQtdNecessaria());

        db.insert("Produtos", null, dados);
    }
}
