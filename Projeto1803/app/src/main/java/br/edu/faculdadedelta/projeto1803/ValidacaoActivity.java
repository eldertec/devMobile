package br.edu.faculdadedelta.projeto1803;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvIdade;
    private TextView tvNascimento;

    private String nome;
    private String idade;
    private String dtNascimento;

    public static final int RESULT_SUCESS = 1;
    public static final int RESULT_ERRO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            idade = intent.getStringExtra("idadeParam");
            dtNascimento = intent.getStringExtra("nascimentoParam");


            tvNome = (TextView) findViewById(R.id.tvNome);
            tvNome.setText(nome);
            tvIdade = (TextView) findViewById(R.id.tvIdade);
            tvIdade.setText(idade);
            tvNascimento = (TextView) findViewById(R.id.tvNascimento);
            tvNascimento.setText(dtNascimento);

        }
    }

    public void validar(View view) {
        int idadeConvertida = Integer.parseInt(idade);
        String msgRetorno = "";
        if (idadeConvertida <= 11) {
            msgRetorno = "Criança";
        } else if (idadeConvertida >= 12 && idadeConvertida < 18) {
            msgRetorno = "Adolecente";
        } else if (idadeConvertida >= 18 && idadeConvertida < 60) {
            msgRetorno = "Adulto";
        } else {
            msgRetorno = "Idoso";
        }

        Intent intent = new Intent();
        intent.putExtra("msgRetorno", msgRetorno);

        setResult(RESULT_SUCESS, intent);

        finish();
    }

    public void voltar(View view) {
        finish();
    }
}
