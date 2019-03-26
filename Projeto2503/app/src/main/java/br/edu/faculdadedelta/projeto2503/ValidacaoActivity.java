package br.edu.faculdadedelta.projeto2503;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvEndereco;
    private TextView tvNascimento;

    private String nome;
    private String endereco;
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
            tvNome = (TextView) findViewById(R.id.tvNome);
            tvNome.setText("Nome: " + nome);

            endereco = intent.getStringExtra("enderecoParam");
            tvEndereco = (TextView) findViewById(R.id.tvEndereco);
            tvEndereco.setText("Endereço: " + endereco);

            dtNascimento = intent.getStringExtra("nascimentoParam");
            tvNascimento = (TextView) findViewById(R.id.tvNascimento);
            tvNascimento.setText("Data de Nascimento: " + dtNascimento);
        }
    }

    public void validar(View view) {
        String msgRetorno = "";

        if (nome.equals("")) {
            msgRetorno = "O nome é obrigatório";
        }

        if (endereco.equals("")) {
            msgRetorno += "\nO endereço é obrigatório";
        }

        if (dtNascimento.equals("")) {
            msgRetorno += "\nA data de nascimento é obrigatória";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataTemp = sdf.parse(dtNascimento);
                double total = ((new Date().getTime()) - dataTemp.getTime());
                double idade = ((((total / 1000) / 60) / 60) / 24) / 360;

                if (idade > 18) {
                    msgRetorno += "\nIdade válida \nIdade: " + idade;
                } else {
                    msgRetorno += "\nIdade Inválida \nIdade: " + idade;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (msgRetorno.equals("")) {
            msgRetorno = "Dados foram validados";
        }

        Intent intent = new Intent();
        intent.putExtra("msgRetorno", msgRetorno);

        setResult(RESULT_SUCESS, intent);

        finish();
    }
}
