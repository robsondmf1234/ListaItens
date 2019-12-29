package com.example.listaitens.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.listaitens.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robson on 15/12/2019
 */
//ProdutoDao extend de SQLOpenhelper para poder trabalhar com função relativas ao SQL
public class ProdutoDAO extends SQLiteOpenHelper {
    
    //Contrutor do ProdutoDao
    public ProdutoDAO(@Nullable Context context) {
        //parametros passado(contexto, nome do banco de dados,arquivo para customizar o banco, versao do banco de dados)
        super(context, "Agenda", null, 1);
    }
    
    //método para criar o banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, qtdAtual TEXT, qtdNecessaria TEXT);";
        db.execSQL(sql);
    }
    
    //método onUpgrade é usado , quando o banco ja tiver sido criado e for detectado que ja tem uma versao atualizada do banco 
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Produtos";
        db.execSQL(sql);
        onCreate(db);
    }

    //Inserindo valores no Banco
    public void insert(Produto produto) {
        //
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoProduto(produto);

        //metodo para inserir informações no banco(tabela,se sera incluido linhas em branco no banco, dados == content values ,local de onde vem as informações )
        db.insert("Produtos", null, dados);
    }

    private ContentValues pegaDadosDoProduto(Produto produto) {
        //Content Values sera usado para armazenar os dados para serem inseridos no comando sql.
        ContentValues dados = new ContentValues();
        //Inserindo o valor na variavel dados Ex:(Chave, valor ) do map do Java.
        dados.put("nome", produto.getNome());
        dados.put("qtdAtual", produto.getQtdAtual());
        dados.put("qtdNecessaria", produto.getQtdNecessaria());
        return dados;
    }

    public List<Produto> buscaProdutos() {
        String sql = "SELECT * FROM Produtos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<Produto>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            produto.setQtdAtual(c.getString(c.getColumnIndex("qtdAtual")));
            produto.setQtdNecessaria(c.getString(c.getColumnIndex("qtdNecessaria")));

            produtos.add(produto);
        }
        c.close();

        return produtos;
    }

    public void delete(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {produto.getId().toString()};
        db.delete("Produtos", "id = ?", params);
    }

    public void altera(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosDoProduto(produto);

        String[]params = {produto.getId().toString()};

        db.update("Produtos",dados, "id= ?",params);
    }
}
