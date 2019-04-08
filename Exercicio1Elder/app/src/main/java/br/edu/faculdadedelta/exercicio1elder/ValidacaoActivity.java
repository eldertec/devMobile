package br.edu.faculdadedelta.exercicio1elder;

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
    private TextView tvDtNascimento;
    private TextView tvPeso;
    private TextView tvAltura;

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
            tvNome = (TextView) findViewById(R.id.tvNome);
            tvDtNascimento = (TextView) findViewById(R.id.tvNascimento);
            tvPeso = (TextView) findViewById(R.id.tvPeso);
            tvAltura = (TextView) findViewById(R.id.tvAltura);

            nome = intent.getStringExtra("nomeParam");
            dtNascimento = intent.getStringExtra("nascimentoParam");
            peso = intent.getStringExtra("pesoParam");
            altura = intent.getStringExtra("alturaParam");

            tvNome.setText("Nome: " + nome);
            tvDtNascimento.setText("Data de Nascimento: " + dtNascimento);
            tvPeso.setText("Peso: " + peso);
            tvAltura.setText("Altura: " + altura);
        }
    }

    public void validar(View view) {
        String msgRetorno = validarCampos();

        if (msgRetorno.equals("")) {

            if (nome.length() <= 30) {
                msgRetorno = "O nome deve ter mais que 30 caracteres!";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataTemp = sdf.parse(dtNascimento);
                if (dataTemp.after(new Date())) {
                    msgRetorno += "\nA data de nascimento deve ser anterior a data atual!";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            double pesoTemp = Double.parseDouble(peso);
            if (pesoTemp <= 60.4) {
                msgRetorno += "\nO peso deve ser maior que 60,4Kg";
            }

            if (msgRetorno.equals("")) {
                msgRetorno = "Todos os dados são válidos";
            }

            Intent intent = new Intent();
            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();
        }else{
            Intent intent = new Intent();
            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();
        }

    }

    private String validarCampos() {
        String msgRetorno = "";

        if (nome.equals("")) {
            msgRetorno = "O nome é obrigatório!";
        }
        if (dtNascimento.equals("")) {
            msgRetorno += "\nA data de nascimento é obrigatória!";
        }
        if (peso.equals("")) {
            msgRetorno += "\nO peso é obrigatório!";
        }
        if (altura.equals("")) {
            msgRetorno += "\nA altura é obrigatória";
        }

        return msgRetorno;
    }
}
