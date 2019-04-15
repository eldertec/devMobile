package br.edu.faculdadedelta.avaliacaon1elder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCliente;
    private EditText etDtNascimento;
    private EditText etCpf;
    private EditText etServico;
    private EditText etQuantidade;
    private EditText etValor;
    private EditText etDtInicio;
    private EditText etDtFim;

    private Button btnEnviar;
    private Button btnLimpar;

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCliente = findViewById(R.id.formulario_cliente);
        etDtNascimento = findViewById(R.id.formulario_dtNascimento);
        etCpf = findViewById(R.id.formulario_cpf);
        etServico = findViewById(R.id.formulario_servico);
        etQuantidade = findViewById(R.id.formulario_quantidade);
        etValor = findViewById(R.id.formulario_valor);
        etDtInicio = findViewById(R.id.formulario_dtInicio);
        etDtFim = findViewById(R.id.formulario_dtFim);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });

        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });

    }

    private void limpar(){
        etCliente.setText("");
        etDtNascimento.setText("");
        etCpf.setText("");
        etServico.setText("");
        etQuantidade.setText("");
        etValor.setText("");
        etDtInicio.setText("");
        etDtFim.setText("");
    }

    private void enviar(){

        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("clienteParam", etCliente.getText().toString());
        intent.putExtra("dtNascimentoParam", etDtNascimento.getText().toString());
        intent.putExtra("cpfParam", etCpf.getText().toString());
        intent.putExtra("servicoParam", etServico.getText().toString());
        intent.putExtra("quantidadeParam", etQuantidade.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("dtInicioParam", etDtInicio.getText().toString());
        intent.putExtra("dtFimParam", etDtFim.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == ValidacaoActivity.RESULT_SUCESS){
                String msg = data.getStringExtra("msgRetorno");

                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

                if(msg.equals("Dados Válidos!")){
                    limpar();
                }
            }
        }
    }
}
