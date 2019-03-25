package br.edu.faculdadedelta.exercicio1elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvDtNascimento;
    private TextView tvPeso;
    private TextView tvAltura;

    private String nome;
    private String dtNascimento;
    private String peso;
    private String altura;

    public static final int RESULT_SUCESS = 1;
    public static final int RESULT_ERRO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if(intent != null){
            tvNome = (TextView) findViewById(R.id.tvNome);
            tvDtNascimento = (TextView) findViewById(R.id.tvNascimento);
            tvPeso = (TextView) findViewById(R.id.tvPeso);
            tvAltura = (TextView) findViewById(R.id.tvAltura);

            nome = intent.getStringExtra("nomeParam");
            dtNascimento = intent.getStringExtra("nascimentoParam");
            peso = intent.getStringExtra("pesoParam");
            altura = intent.getStringExtra("alturaParam");

            tvNome.setText(nome);
            tvDtNascimento.setText(dtNascimento);
            tvPeso.setText(peso);
            tvAltura.setText(altura);
        }
    }
}
