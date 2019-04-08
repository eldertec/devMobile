package br.edu.faculdadedelta.exercicio4eldermd;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ValidacaoActivity extends AppCompatActivity {

    private TextInputEditText etProduto;
    private TextInputEditText etValor;
    private TextInputEditText etFornecedor;
    private TextInputEditText etQuantidade;

    private MaterialButton btnValidar;

    private String produto;
    private String valor;
    private String fornecedor;
    private String quantidade;

    public static final int RESULT_SUCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            etProduto = findViewById(R.id.tvProduto);
            etValor = findViewById(R.id.tvValor);
            etFornecedor = findViewById(R.id.tvFornecedor);
            etQuantidade = findViewById(R.id.tvQuantidade);

            produto = intent.getStringExtra("produtoParam");
            valor = intent.getStringExtra("valorParam");
            fornecedor = intent.getStringExtra("fornecedorParam");
            quantidade = intent.getStringExtra("quantidadeParam");

            etProduto.setText(produto);
            etValor.setText(valor);
            etFornecedor.setText(fornecedor);
            etQuantidade.setText(quantidade);
        }

        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgRetorno = validar();
                Intent intent = new Intent();
                intent.putExtra("msgRetorno", msgRetorno);

                setResult(RESULT_SUCESS, intent);
                finish();
            }
        });
    }

    private String validar() {
        String msgRetorno = "";

        double valorConvertido = Double.parseDouble(etValor.getText().toString());
        int qtdConvertido = Integer.parseInt(etQuantidade.getText().toString());

        if (valorConvertido <= 500) {
            msgRetorno = "O Valor deve ser maior que 500!";
        }
        if (qtdConvertido < 1) {
            msgRetorno += "\nA Quantidade deve ser maior que 0!";
        }
        if (msgRetorno.equals("")) {
            msgRetorno = "Dados Válidos!";
        }

        return msgRetorno;
    }
}
