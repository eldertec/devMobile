package br.edu.faculdadedelta.exercicio2elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvNome;
    private TextView tvValor;
    private TextView tvCor;
    private TextView tvModelo;

    private String nome;
    private String valor;
    private String cor;
    private String modelo;

    public static final int RESULT_SUCESS = 1;
    public static final int RESULT_ERRO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvNome = findViewById(R.id.tvNome);
        tvValor = findViewById(R.id.tvValor);
        tvCor = findViewById(R.id.tvCor);
        tvModelo = findViewById(R.id.tvModelo);

        Intent intent = getIntent();
        if (intent != null) {
            nome = intent.getStringExtra("nomeParam");
            tvNome.setText("Nome: " + nome);

            valor = intent.getStringExtra("valorParam");
            tvValor.setText("Valor: " + valor);

            cor = intent.getStringExtra("corParam");
            tvCor.setText("Cor: " + cor);

            modelo = intent.getStringExtra("modeloParam");
            tvModelo.setText("Modelo: " + modelo);


        }
    }

    public void validar(View view) {
        String msgRetorno = validarCampos();
        Intent intent = new Intent();

        if (msgRetorno.equals("")) {
            double valorConvertido = Double.parseDouble(valor);

            if (nome.length() <= 30) {
                msgRetorno = "O Nome deve ter mais que 30 caracteres!";
            }
            if (valorConvertido <= 1000) {
                msgRetorno += "\nO Valor deve ser maior que 1000!";
            }
            if (modelo.length() <= 40) {
                msgRetorno += "\nO Modelo deve ter mais que 40 caracteres!";
            }
            if (msgRetorno.equals("")) {
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

    private String validarCampos() {
        String msgRetorno = "";

        if (nome.equals("")) {
            msgRetorno = "O Nome é obrigatório!";
        }
        if (valor.equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        }
        if (cor.equals("")) {
            msgRetorno += "\nA Cor é obrigatória!";
        }
        if (modelo.equals("")) {
            msgRetorno += "\nO Modelo é obrigatório!";
        }
        return msgRetorno;
    }
}
