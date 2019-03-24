package br.edu.faculdadedelta.projeto1803;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private EditText etNascimento;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.formulario_nome);
        etIdade = (EditText) findViewById(R.id.formulario_idade);
        etNascimento = (EditText) findViewById(R.id.formulario_dtNascimento);
    }

    public void enviar(View view) {

        String msg = validarFormulario();

        if (msg.equals("")) {

            Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

            intent.putExtra("nomeParam", etNome.getText().toString());
            intent.putExtra("idadeParam", etIdade.getText().toString());
            intent.putExtra("nascimentoParam", etNascimento.getText().toString());

            startActivityForResult(intent, REQUEST_CODE);

        } else {
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public String validarFormulario() {
        String msgErro = "";

        if (etNome.getText().toString().equals("")) {
            msgErro = "O nome é obrigatório";
        }
        if (etIdade.getText().toString().equals("")) {
            msgErro += "\nA idade é obrigatória";
        }
        if (etNascimento.getText().toString().equals("")) {
            msgErro += "\nA data de nascimento é obrigatória";
        }

        return msgErro;
    }

    public void limpar(View... view) {
        etNome.setText("");
        etIdade.setText("");
        etNascimento.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == ValidacaoActivity.RESULT_SUCESS){
                String msg = data.getStringExtra("msgRetorno");

                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

                limpar();
            }
        }
    }
}
