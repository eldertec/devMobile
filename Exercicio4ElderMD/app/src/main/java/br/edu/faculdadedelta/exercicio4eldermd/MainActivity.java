package br.edu.faculdadedelta.exercicio4eldermd;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private TextInputEditText etValor;
    private TextInputEditText etProduto;
    private TextInputEditText etFornecedor;
    private TextInputEditText etQuantidade;

    private TextInputLayout tlProduto;
    private TextInputLayout tlValor;
    private TextInputLayout tlFornecedor;
    private TextInputLayout tlQuantidade;

    private MaterialButton btnEnviar;
    private MaterialButton btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tlValor = findViewById(R.id.hintValor);
        tlProduto = findViewById(R.id.hintProduto);
        tlFornecedor = findViewById(R.id.hintFornecedor);
        tlQuantidade = findViewById(R.id.hintQuantidade);

        etProduto = findViewById(R.id.formProduto);
        etValor = findViewById(R.id.formValor);
        etFornecedor = findViewById(R.id.formFornecedor);
        etQuantidade = findViewById(R.id.formQuantidade);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validador()) {
                    enviar();
                }

            }
        });

        btnLimpar = findViewById(R.id.btnlimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });


    }

    private void enviar() {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("fornecedorParam", etFornecedor.getText().toString());
        intent.putExtra("quantidadeParam", etQuantidade.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    private void limparCampos() {
        etProduto.setText("");
        tlProduto.setError(null);
        etValor.setText("");
        tlValor.setError(null);
        etFornecedor.setText("");
        tlFornecedor.setError(null);
        etQuantidade.setText("");
        tlQuantidade.setError(null);
    }

    private boolean validador() {
        boolean valida = true;
        String erro = getString(R.string.res_erroForm);
        if (!isProdutoValid(etProduto.getText())) {
            tlProduto.setError(erro.concat(getString(R.string.res_erroProduto)));
            valida = false;
        } else {
            tlProduto.setError(null);
        }
        if (!isValorValid(etValor.getText())) {
            tlValor.setError(erro);
            valida = false;
        } else {
            tlValor.setError(null);
        }
        if (!isFornecedorValid(etFornecedor.getText())) {
            tlFornecedor.setError(erro.concat(getString(R.string.res_erroFornecedor)));
            valida = false;
        } else {
            tlFornecedor.setError(null);
        }
        if (!isQuantidadeValid(etQuantidade.getText())) {
            tlQuantidade.setError(erro);
            valida = false;
        } else {
            tlQuantidade.setError(null);
        }
        return valida;
    }

    private boolean isProdutoValid(@Nullable Editable text) {
        return text != null && text.length() > 15;
    }

    private boolean isValorValid(@Nullable Editable text) {
        return text != null && text.length() > 1;
    }

    private boolean isFornecedorValid(@Nullable Editable text) {
        return text != null && text.length() > 11;
    }

    private boolean isQuantidadeValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ValidacaoActivity.RESULT_SUCESS) {
                String msg = data.getStringExtra("msgRetorno");
                LayoutInflater inflater = getLayoutInflater();

                if (msg.equals("Dados Válidos!")) {
                    limparCampos();
                    View layout = inflater.inflate(R.layout.toast_valido,
                            (ViewGroup) findViewById(R.id.custom_toast));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(msg);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                } else {
                    View layout = inflater.inflate(R.layout.toast_style,
                            (ViewGroup) findViewById(R.id.custom_toast));

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setText(msg);

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }

            }
        }
    }
}
