package br.edu.faculdadedelta.exercicio2elder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etValor;
    private EditText etCor;
    private EditText etModelo;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.formulario_nome);
        etValor = findViewById(R.id.formulario_valor);
        etCor = findViewById(R.id.formulario_cor);
        etModelo = findViewById(R.id.formulario_modelo);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("corParam", etCor.getText().toString());
        intent.putExtra("modeloParam", etModelo.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etNome.setText("");
        etCor.setText("");
        etValor.setText("");
        etModelo.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ValidacaoActivity.RESULT_SUCESS) {
                String msg = data.getStringExtra("msgRetorno");
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
                if (msg.equals("Dados Válidos!")) {
                    limpar(null);
                }
            }
        }
    }
}
