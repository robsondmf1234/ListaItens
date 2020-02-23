package com.example.listaitens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

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
    private final EditText campoEndereco;
    private final ImageView campoFoto;

    private Produto produto;
    
    //Passando uma referencia da activity para o FormularioHelper, pode utilizar o findviewByid()
    public FormularioHelper(FormularioActivity activity) {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoQtdAtual = (EditText) activity.findViewById(R.id.formulario_qtdAtual);
        campoQtdNecessaria = (EditText) activity.findViewById(R.id.formulario_qtdNecessaria);
        campoEndereco = (EditText)activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);

        //Instanciando um novo Produto
        produto = new Produto();
    }

    public Produto pegaProduto() {

        //Setando o nome com o valor obtido da variavel campoNome
        produto.setNome(campoNome.getText().toString());
        produto.setQtdAtual(campoQtdAtual.getText().toString());
        produto.setQtdNecessaria(campoQtdNecessaria.getText().toString());
        produto.setEndereco(campoEndereco.getText().toString());
        produto.setTelefone(campoTelefone.getText().toString());
        produto.setSite(campoSite.getText().toString());
        produto.setCaminhoFoto((String) campoFoto.getTag());

        //retonando o objeto produto
        return produto;
    }

    public void preencheFormulario(Produto produto) {
        campoNome.setText(produto.getNome());
        campoQtdAtual.setText(produto.getQtdAtual());
        campoQtdNecessaria.setText(produto.getQtdNecessaria());
        campoEndereco.setText(produto.getEndereco());
        campoTelefone.setText(produto.getTelefone());
        campoSite.setText(produto.getSite());
        carregaImagem(produto.getCaminhoFoto());

        this.produto = produto;
    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
        }
    }
}
