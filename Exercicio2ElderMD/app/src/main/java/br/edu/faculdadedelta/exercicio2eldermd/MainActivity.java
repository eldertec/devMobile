package br.edu.faculdadedelta.exercicio2eldermd;

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
    private TextInputEditText etNome;
    private TextInputEditText etCor;
    private TextInputEditText etModelo;

    private TextInputLayout tlNome;
    private TextInputLayout tlValor;
    private TextInputLayout tlCor;
    private TextInputLayout tlModelo;

    private MaterialButton btnEnviar;
    private MaterialButton btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tlValor = findViewById(R.id.hintValor);
        tlNome = findViewById(R.id.hintNome);
        tlCor = findViewById(R.id.hintCor);
        tlModelo = findViewById(R.id.hintModelo);

        etNome = findViewById(R.id.formNome);
        etValor = findViewById(R.id.formValor);
        etCor = findViewById(R.id.formCor);
        etModelo = findViewById(R.id.formModelo);

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

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("corParam", etCor.getText().toString());
        intent.putExtra("modeloParam", etModelo.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    private void limparCampos() {
        etNome.setText("");
        tlNome.setError(null);
        etValor.setText("");
        tlValor.setError(null);
        etCor.setText("");
        tlCor.setError(null);
        etModelo.setText("");
        tlModelo.setError(null);
    }

    private boolean validador() {
        boolean valida = true;
        String erro = getString(R.string.res_erroForm);
        if (!isNomeValid(etNome.getText())) {
            tlNome.setError(erro);
            valida = false;
        } else {
            tlNome.setError(null);
        }
        if (!isValorValid(etValor.getText())) {
            tlValor.setError(erro);
            valida = false;
        } else {
            tlValor.setError(null);
        }
        if (!isCorValid(etCor.getText())) {
            tlCor.setError(erro);
            valida = false;
        } else {
            tlCor.setError(null);
        }
        if (!isModeloValid(etModelo.getText())) {
            tlModelo.setError(erro);
            valida = false;
        } else {
            tlModelo.setError(null);
        }
        return valida;
    }

    private boolean isNomeValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isValorValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isCorValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isModeloValid(@Nullable Editable text) {
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
