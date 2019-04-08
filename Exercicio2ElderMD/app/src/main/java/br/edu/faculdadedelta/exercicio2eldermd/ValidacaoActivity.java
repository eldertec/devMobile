package br.edu.faculdadedelta.exercicio2eldermd;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ValidacaoActivity extends AppCompatActivity {

    private TextInputEditText etNome;
    private TextInputEditText etValor;
    private TextInputEditText etCor;
    private TextInputEditText etModelo;

    private MaterialButton btnValidar;

    private String nome;
    private String valor;
    private String cor;
    private String modelo;

    public static final int RESULT_SUCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            etNome = findViewById(R.id.tvNome);
            etValor = findViewById(R.id.tvValor);
            etCor = findViewById(R.id.tvCor);
            etModelo = findViewById(R.id.tvModelo);

            nome = intent.getStringExtra("nomeParam");
            valor = intent.getStringExtra("valorParam");
            cor = intent.getStringExtra("corParam");
            modelo = intent.getStringExtra("modeloParam");

            etNome.setText(nome);
            etValor.setText(valor);
            etCor.setText(cor);
            etModelo.setText(modelo);
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

        if (etNome.getText().toString().length() <= 30) {
            msgRetorno = "O Nome deve ter mais que 30 caracteres!";
        }
        if (etModelo.getText().toString().length() <= 40) {
            msgRetorno += "\nO Modelo deve ter mais que 40 caracteres!";
        }
        if (valorConvertido <= 1000) {
            msgRetorno += "\nO Valor deve ser maior que 1000!";
        }
        if (msgRetorno.equals("")) {
            msgRetorno = "Dados Válidos!";
        }

        return msgRetorno;
    }
}
