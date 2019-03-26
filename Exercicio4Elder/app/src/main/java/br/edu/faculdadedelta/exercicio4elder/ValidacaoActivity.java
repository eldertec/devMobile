package br.edu.faculdadedelta.exercicio4elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvProduto;
    private TextView tvValor;
    private TextView tvFornecedor;
    private TextView tvQuantidade;

    private String produto;
    private String valor;
    private String fornecedor;
    private String quantidade;

    public static final int RESULT_SUCESS = 1;
    public static final int RESULT_ERRO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvProduto = findViewById(R.id.tvProduto);
        tvValor = findViewById(R.id.tvValor);
        tvFornecedor = findViewById(R.id.tvFornecedor);
        tvQuantidade = findViewById(R.id.tvQuantidade);

        Intent intent = getIntent();
        if (intent != null) {

            produto = intent.getStringExtra("produtoParam");
            tvProduto.setText("Produto: " + produto);

            fornecedor = intent.getStringExtra("fornecedorParam");
            tvFornecedor.setText("Fornecedor: " + fornecedor);

            valor = intent.getStringExtra("valorParam");
            tvValor.setText("Valor: " + valor);

            quantidade = intent.getStringExtra("quantidadeParam");
            tvQuantidade.setText("Quantidade: " + quantidade);
        }
    }

    public void validar(View view) {
        String msgRetorno = validarFormulario();
        Intent intent = new Intent();

        if (msgRetorno.equals("")) {

            if(produto.length() <= 15){
                msgRetorno = "O nome do Produto deve ter mais que 15 caracteres";
            }

            double valorConvertido = Double.parseDouble(valor);
            if(valorConvertido <= 500){
                msgRetorno += "\nO Valor deve ser maior que 500";
            }

            if(fornecedor.length() <= 11){
                msgRetorno += "\nO nome do Fornecedor deve ter mais que 11 caracteres";
            }

            int qtdConvertida = Integer.parseInt(quantidade);
            if(qtdConvertida < 1){
                msgRetorno += "\nA Quantidade deve ser maior que zero";
            }

            if(msgRetorno.equals("")){
                msgRetorno = "Dados Válidos!";
            }

            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();

        } else {

            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();
        }
    }

    private String validarFormulario() {
        String msgRetorno = "";

        if (produto.equals("")) {
            msgRetorno = "O Produto é obrigatório";
        }
        if (valor.equals("")) {
            msgRetorno += "\nO Valor é obrigatório";
        }
        if (fornecedor.equals("")) {
            msgRetorno += "\nO Fornecedor é obrigatório";
        }
        if (quantidade.equals("")) {
            msgRetorno += "\nA Quantidade é obrigatória";
        }

        return msgRetorno;
    }
}
