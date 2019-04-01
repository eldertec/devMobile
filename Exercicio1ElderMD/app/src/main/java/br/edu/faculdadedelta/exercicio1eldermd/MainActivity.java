package br.edu.faculdadedelta.exercicio1eldermd;


import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView teste;

    Calendar calendar;
    int Year, Month, Day;

    TextInputEditText dtNascimento;
    TextInputLayout hintDtNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = Calendar.getInstance();

        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);


        dtNascimento = findViewById(R.id.formDtNascimento);
        dtNascimento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog datePickerDialog =
                            new DatePickerDialog(MainActivity.this, null, Year, Month, Day);
                    datePickerDialog.show();
                }
            }
        });


    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dtNascimento.setText(dayOfMonth + "/" + month + "/" + year);
        Toast.makeText(getBaseContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
    }
}
