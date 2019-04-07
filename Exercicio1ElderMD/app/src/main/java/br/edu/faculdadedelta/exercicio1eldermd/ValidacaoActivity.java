package br.edu.faculdadedelta.exercicio1eldermd;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ValidacaoActivity extends AppCompatActivity {

    private TextInputEditText etNome;
    private TextInputEditText etPeso;
    private TextInputEditText etDtNascimento;
    private TextInputEditText etAltura;

    private MaterialButton btnValidar;

    private String nome;
    private String dtNascimento;
    private String peso;
    private String altura;

    public static final int RESULT_SUCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            etNome = findViewById(R.id.tvNome);
            etDtNascimento = findViewById(R.id.tvDtNascimento);
            etPeso = findViewById(R.id.tvPeso);
            etAltura = findViewById(R.id.tvAltura);

            nome = intent.getStringExtra("nomeParam");
            dtNascimento = intent.getStringExtra("dtNascimentoParam");
            peso = intent.getStringExtra("pesoParam");
            altura = intent.getStringExtra("alturaParam");

            etNome.setText(nome);
            etDtNascimento.setText(dtNascimento);
            etPeso.setText(peso);
            etAltura.setText(altura);
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

        double pesoConvertido = Double.parseDouble(etPeso.getText().toString());

        if (etNome.getText().toString().length() <= 30) {
            msgRetorno = "O Nome deve ter mais que 30 caracteres!";
        }
        if (pesoConvertido <= 60.4) {
            msgRetorno += "\nO Peso deve ser maior que 60.4!";
        }
        if (msgRetorno.equals("")) {
            msgRetorno = "Dados Válidos!";
        }

        return msgRetorno;
    }
}

