package br.edu.faculdadedelta.exercicio3eldermd;


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

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    private static final int REQUEST_CODE = 1;
    private Calendar calendar;
    private int Year, Month, Day;
    private DatePickerDialog datePickerDialog;

    private TextInputEditText etProduto;
    private TextInputEditText etValor;
    private TextInputEditText etCliente;
    private TextInputEditText etDtVenda;

    private TextInputLayout tlProduto;
    private TextInputLayout tlValor;
    private TextInputLayout tlCliente;
    private TextInputLayout tlDtVenda;

    private MaterialButton btnEnviar;
    private MaterialButton btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        tlCliente = findViewById(R.id.hintCliente);
        tlProduto = findViewById(R.id.hintProduto);
        tlValor = findViewById(R.id.hintValor);
        tlDtVenda = findViewById(R.id.hintDtVenda);

        etValor = findViewById(R.id.formValor);
        etCliente = findViewById(R.id.formCliente);
        etProduto = findViewById(R.id.formProduto);
        etDtVenda = findViewById(R.id.formDtVenda);
        etDtVenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, Year, Month, Day);
                datePickerDialog.setAccentColor("#CD853F");
                datePickerDialog.setCancelColor("#A52A2A");
                datePickerDialog.setOkColor("#0000FF");
                calendar.set(Calendar.DAY_OF_MONTH, 2);
                calendar.set(Calendar.MONTH, 0);
                calendar.set(Calendar.YEAR, 2014);
                datePickerDialog.setMinDate(calendar);
                datePickerDialog.setTitle("Data da Venda");
                datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
            }
        });


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

        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("clienteParam", etCliente.getText().toString());
        intent.putExtra("produtoParam", etProduto.getText().toString());
        intent.putExtra("dtVendaParam", etDtVenda.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    private void limparCampos() {
        etValor.setText("");
        tlValor.setError(null);
        etCliente.setText("");
        tlCliente.setError(null);
        etDtVenda.setText("");
        tlDtVenda.setError(null);
        etProduto.setText("");
        tlProduto.setError(null);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dataSelecionada = String.format("%02d/%02d/%d", dayOfMonth, ++monthOfYear, year);
        etDtVenda.setText(dataSelecionada);
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
        if (!isClienteValid(etCliente.getText())) {
            tlCliente.setError(erro);
            valida = false;
        } else {
            tlCliente.setError(null);
        }
        if (!isDataValid(etDtVenda.getText())) {
            tlDtVenda.setError(erro);
            valida = false;
        } else {
            tlDtVenda.setError(null);
        }
        return valida;
    }

    private boolean isProdutoValid(@Nullable Editable text) {
        return text != null && text.length() > 10;
    }

    private boolean isValorValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isClienteValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isDataValid(@Nullable Editable text) {
        return text != null && text.length() > 8;
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
