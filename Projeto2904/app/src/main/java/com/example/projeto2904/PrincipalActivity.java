package com.example.projeto2904;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projeto2904.dao.SeriadoDao;
import com.example.projeto2904.modelo.Seriado;

public class PrincipalActivity extends AppCompatActivity {

    private PrincipalHelper helper;

    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        helper = new PrincipalHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            Seriado seriadoSelecionado = (Seriado) intent.getSerializableExtra("seriadoSelecionado");
            if (seriadoSelecionado != null) {
                helper.popularFormulario(seriadoSelecionado);
            }
        }

        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.limparCampos();
            }
        });

        btnListar = findViewById(R.id.btnListar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }


    private void listar() {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void salvar() {
        String msgRetorno = helper.validarCampos();

        if (msgRetorno.equals("")) {
            Seriado seriado = helper.popularModelo();
            SeriadoDao dao = new SeriadoDao();
            if (seriado.getId() == null) {
                dao.incluir(seriado);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(seriado);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }
}
