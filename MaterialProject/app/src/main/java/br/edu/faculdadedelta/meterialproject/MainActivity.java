package br.edu.faculdadedelta.meterialproject;


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

    private TextInputEditText dtNascimento;
    private TextInputEditText nome;
    private TextInputEditText peso;
    private TextInputEditText altura;

    private TextInputLayout tlNome;
    private TextInputLayout tlPeso;
    private TextInputLayout tlAltura;
    private TextInputLayout tlDtNascimento;

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

        tlAltura = findViewById(R.id.hintAltura);
        tlNome = findViewById(R.id.hintNome);
        tlPeso = findViewById(R.id.hintPeso);
        tlDtNascimento = findViewById(R.id.hintDtNascimento);

        nome = findViewById(R.id.formNome);
        peso = findViewById(R.id.formPeso);
        altura = findViewById(R.id.formAltura);
        dtNascimento = findViewById(R.id.formDtNascimento);
        dtNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = DatePickerDialog.newInstance(MainActivity.this, Year, Month, Day);
                datePickerDialog.setAccentColor("#696969");
                datePickerDialog.setCancelColor("#DCDCDC");
                datePickerDialog.setOkColor("#DCDCDC");
                calendar.set(Calendar.DAY_OF_MONTH, Day - 1);
                datePickerDialog.setMaxDate(calendar);
                datePickerDialog.setTitle("Data de Nascimento");
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

        intent.putExtra("nomeParam", nome.getText().toString());
        intent.putExtra("pesoParam", peso.getText().toString());
        intent.putExtra("dtNascimentoParam", dtNascimento.getText().toString());
        intent.putExtra("alturaParam", altura.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    private void limparCampos() {
        nome.setText("");
        tlNome.setError(null);
        peso.setText("");
        tlPeso.setError(null);
        altura.setText("");
        tlAltura.setError(null);
        dtNascimento.setText("");
        tlDtNascimento.setError(null);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String dataSelecionada = String.format("%02d/%02d/%d", dayOfMonth, ++monthOfYear, year);
        dtNascimento.setText(dataSelecionada);
    }


    private boolean validador() {
        boolean valida = true;
        String erro = getString(R.string.res_erroForm);
        if (!isNomeValid(nome.getText())) {
            tlNome.setError(erro);
            valida = false;
        } else {
            tlNome.setError(null);
        }
        if (!isPesoValid(peso.getText())) {
            tlPeso.setError(erro);
            valida = false;
        } else {
            tlPeso.setError(null);
        }
        if (!isAlturaValid(altura.getText())) {
            tlAltura.setError(erro.concat(getString(R.string.res_erroAltura)));
            valida = false;
        } else {
            tlAltura.setError(null);
        }
        if (!isDataValid(dtNascimento.getText())) {
            tlDtNascimento.setError(erro);
            valida = false;
        } else {
            tlDtNascimento.setError(null);
        }
        return valida;
    }

    private boolean isNomeValid(@Nullable Editable text) {
        return text != null && text.length() > 0;
    }

    private boolean isPesoValid(@Nullable Editable text) {
        return text != null && text.length() > 1;
    }

    private boolean isAlturaValid(@Nullable Editable text) {
        return text != null && text.length() > 2;
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

