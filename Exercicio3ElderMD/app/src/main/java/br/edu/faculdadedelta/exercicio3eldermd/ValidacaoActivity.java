package br.edu.faculdadedelta.exercicio3eldermd;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ValidacaoActivity extends AppCompatActivity {

    private TextInputEditText etProduto;
    private TextInputEditText etValor;
    private TextInputEditText etCliente;
    private TextInputEditText etDtVenda;

    private MaterialButton btnValidar;

    private String produto;
    private String valor;
    private String cliente;
    private String dtVenda;

    public static final int RESULT_SUCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            etProduto = findViewById(R.id.tvProduto);
            etCliente = findViewById(R.id.tvCliente);
            etValor = findViewById(R.id.tvValor);
            etDtVenda = findViewById(R.id.tvDtVenda);

            produto = intent.getStringExtra("produtoParam");
            valor = intent.getStringExtra("valorParam");
            cliente = intent.getStringExtra("clienteParam");
            dtVenda = intent.getStringExtra("dtVendaParam");

            etProduto.setText(produto);
            etCliente.setText(cliente);
            etValor.setText(valor);
            etDtVenda.setText(dtVenda);
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

        if (valorConvertido <= 658) {
            msgRetorno = "O Valor deve ser maior que 658!";
        }
        if (etCliente.getText().toString().length() <= 40) {
            msgRetorno += "\nO Cliente deve ter mais que 40 caracteres!";
        }
        if (msgRetorno.equals("")) {
            msgRetorno = "Dados Válidos!";
        }

        return msgRetorno;
    }
}

