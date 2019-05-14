package com.example.projetoviagem0605;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetoviagem0605.dao.ViagemDao;
import com.example.projetoviagem0605.modelo.Viagem;

import java.time.Period;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    private Button btnSalvar;
    private Button btnListar;
    private Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            Viagem viagemSelecionada = (Viagem) intent.getSerializableExtra("viagemSelecionada");
            if (viagemSelecionada != null) {
                helper.popularFormulario(viagemSelecionada);
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

    private void salvar() {
        String msgRetorno = helper.validarCampos();

        if (msgRetorno.equals("")) {
            Viagem viagem = helper.popularModelo();
            ViagemDao dao = new ViagemDao();

            if((viagem.getDataChegada().getTime() - viagem.getDataSaida().getTime()) > 1728000000){
                double valor = viagem.getValor() - (viagem.getValor() * 0.02);
                viagem.setValor(valor);
            }

            if(viagem.getDestino().equals("Goias")){
                double valor = viagem.getValor() - (viagem.getValor() * 0.001);
                viagem.setValor(valor);
            }

            if (viagem.getId() == null) {
                dao.incluir(viagem);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(viagem);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }

    private void listar() {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }


}
