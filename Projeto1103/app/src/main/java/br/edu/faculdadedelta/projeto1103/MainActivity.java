package br.edu.faculdadedelta.projeto1103;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nome;
    private EditText idade;
    private EditText dtNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.formulario_nome);
        idade = (EditText) findViewById(R.id.formulario_idade);
        dtNascimento = (EditText) findViewById(R.id.formulario_dtNascimento);
    }

    public void enviar(View view){

        String msg = validarFormulario();

        if(msg.equals("")) {

            Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

            intent.putExtra("nomeParam", nome.getText().toString());
            intent.putExtra("idadeParam", idade.getText().toString());
            intent.putExtra("nascimentoParam", dtNascimento.getText().toString());

            startActivity(intent);

        }else{
            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
        }
    }

    public String validarFormulario(){
        String msgErro = "";

        if(nome.getText().toString().equals("")){
            msgErro = "O nome é obrigatório";
        }
        if(idade.getText().toString().equals("")){
            msgErro += "\nA idade é obrigatória";
        }
        if(dtNascimento.getText().toString().equals("")){
            msgErro += "\nA data de nascimento é obrigatória";
        }

        return msgErro;
    }

    public void limpar(View view){
        nome.setText("");
        idade.setText("");
        dtNascimento.setText("");
    }
}
