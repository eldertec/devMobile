package br.edu.faculdadedelta.exercicio07eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import br.edu.faculdadedelta.exercicio07eldern2.dao.MotoristaDaoElder;
import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialButton btnSalvar;
    private MaterialButton btnListar;
    private MaterialButton btnLimpar;

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            MotoristaElder motoristaSelecionado = (MotoristaElder) intent.getSerializableExtra("motoristaSelecionado");
            if (motoristaSelecionado != null) {
                helper.popularFormulario(motoristaSelecionado);
            }
        }

        btnLimpar = findViewById(R.id.btnlimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.limparCampos();
            }
        });

        btnListar = findViewById(R.id.btnlistar);
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

    private void salvar() {
        String msgRetorno = helper.validarCampos();

        if (TextUtils.isEmpty(msgRetorno)) {
            MotoristaElder motorista = helper.popularModelo();
            MotoristaDaoElder dao = new MotoristaDaoElder(this);

            if (motorista.getId() == null) {
                dao.inserir(motorista);
                helper.limparCampos();
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(motorista);
                helper.limparCampos();
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            dao.close();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }

    }

    private void listar() {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }
}
