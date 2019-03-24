package br.edu.faculdadedelta.projeto1103;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ValidacaoActivity extends AppCompatActivity {

    private String nome;
    private String idade;
    private String dtNascimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();

        if(intent != null){
            nome = intent.getStringExtra("nomeParam");
            idade = intent.getStringExtra("idadeParam");
            dtNascimento = intent.getStringExtra("nascimentoParam");

            TextView tvNome = (TextView) findViewById(R.id.tvNome);
            tvNome.setText(nome);

            TextView tvIdade = (TextView) findViewById(R.id.tvIdade);
            tvIdade.setText(idade);

            TextView tvDtNascimento = (TextView) findViewById(R.id.tvNascimento);
            tvDtNascimento.setText(dtNascimento);
        }


    }

    public void validar(View view){
        int idadeConvertida = Integer.parseInt(idade);
        if(idadeConvertida <= 11){
            Toast.makeText(getBaseContext(), "Criança", Toast.LENGTH_SHORT).show();
        }else if(idadeConvertida >= 12 && idadeConvertida < 18){
            Toast.makeText(getBaseContext(), "Adolecente", Toast.LENGTH_SHORT).show();
        }else if(idadeConvertida >= 18 && idadeConvertida < 60){
            Toast.makeText(getBaseContext(), "Adulto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "Idoso", Toast.LENGTH_SHORT).show();
        }
    }

    public void voltar(View view){
       finish();
    }
}
