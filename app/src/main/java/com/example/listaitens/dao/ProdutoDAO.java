package com.example.listaitens.dao;
//Codigo backup
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
        super(context, "Agenda", null, 5);
    }
    
    //método para criar o banco de dados
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL," +
                " qtdAtual TEXT," +
                " qtdNecessaria TEXT," +
                "endereco TEXT,"+
                "telefone TEXT," +
                "site TEXT," +
                "caminhoFoto TEXT);";
        db.execSQL(sql);
    }
    
    //método onUpgrade é usado , quando o banco ja tiver sido criado e for detectado que ja tem uma versao atualizada do banco 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE Produtos ADD COLUMN telefone TEXT";
                db.execSQL(sql);//indo para versao 2

            case 2:
                sql = "ALTER TABLE Produtos ADD COLUMN site TEXT";
                db.execSQL(sql); //indo para versao 3

            case 3:
                sql = "ALTER TABLE Produtos ADD COLUMN endereco TEXT";
                db.execSQL(sql);//indo paa a versao 4

            case 4:
                sql = "ALTER TABLE Produtos ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);//indo para a versao 5
        }
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
        dados.put("endereco",produto.getEndereco());
        dados.put("telefone",produto.getTelefone());
        dados.put("site",produto.getSite());
        dados.put("caminhoFoto", produto.getCaminhoFoto());

        return dados;
    }

    //método para buscar produtos
    public List<Produto> buscaProdutos() {
        //Queries para buscar todos produtos
        String sql = "SELECT * FROM Produtos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<>();
        while (c.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            produto.setQtdAtual(c.getString(c.getColumnIndex("qtdAtual")));
            produto.setQtdNecessaria(c.getString(c.getColumnIndex("qtdNecessaria")));
            produto.setEndereco(c.getString(c.getColumnIndex("endereco")));
            produto.setTelefone(c.getString(c.getColumnIndex("telefone")));
            produto.setSite(c.getString(c.getColumnIndex("site")));
            produto.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

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
