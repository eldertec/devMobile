package br.edu.faculdadedelta.exercicio1elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etDtNascimento;
    private EditText etPeso;
    private EditText etAltura;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.formulario_nome);
        etDtNascimento = (EditText) findViewById(R.id.formulario_dtNascimento);
        etPeso = (EditText) findViewById(R.id.formulario_peso);
        etAltura = (EditText) findViewById(R.id.formulario_altura);


    }

    public void enviar(View view){
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("nascimentoParam", etDtNascimento.getText().toString());
        intent.putExtra("pesoParam", etPeso.getText().toString());
        intent.putExtra("alturaParam", etAltura.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }
}
