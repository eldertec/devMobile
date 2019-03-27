package br.edu.faculdadedelta.exercicio3elder;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etProduto;
    private EditText etValor;
    private EditText etCliente;
    private EditText etDtVenda;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById(R.id.formulario_produto);
        etValor = findViewById(R.id.formulario_valor);
        etCliente = findViewById(R.id.formulario_cliente);
        etDtVenda = findViewById(R.id.formulario_dtVenda);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("clienteParam", etCliente.getText().toString());
        intent.putExtra("dtVendaParam", etDtVenda.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etDtVenda.setText("");
        etCliente.setText("");
        etValor.setText("");
        etProduto.setText("");
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
