package com.example.projetoordemservico1305;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetoordemservico1305.dao.ServicoDao;
import com.example.projetoordemservico1305.modelo.Servico;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnListar;

    private PrincipalHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        helper = new PrincipalHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            Servico servicoSelecionado = (Servico) intent.getSerializableExtra("servicoSelecionado");
            if (servicoSelecionado != null) {
                helper.preencherFormulario(servicoSelecionado);
            }
        }

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        btnListar = findViewById(R.id.btnListar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });

        btnLimpar = findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.limparCampos();
            }
        });

    }

    private void listar(){
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void salvar(){
        String msgRetorno = helper.validarCampos();

        if(msgRetorno.equals("")){
            Servico servico = helper.popularModelo();
            ServicoDao dao = new ServicoDao();
            if (servico.getId() == null) {
                dao.incluir(servico);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(servico);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }
}
