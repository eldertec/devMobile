package br.edu.faculdadedelta.exercicio4elder;

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
    private EditText etFornecedor;
    private EditText etQuantidade;

    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProduto = findViewById(R.id.formulario_produto);
        etValor = findViewById(R.id.formulario_valor);
        etFornecedor = findViewById(R.id.formulario_fornecedor);
        etQuantidade = findViewById(R.id.formulario_quantidade);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("fornecedorParam", etFornecedor.getText().toString());
        intent.putExtra("quantidadeParam", etQuantidade.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etQuantidade.setText("");
        etFornecedor.setText("");
        etValor.setText("");
        etProduto.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ValidacaoActivity.RESULT_SUCESS) {
                String msg = data.getStringExtra("msgRetorno");
                limpar(null);
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();

            }
        }
    }
}
