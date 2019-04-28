package br.edu.faculdadedelta.projeto1504;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.faculdadedelta.projeto1504.dao.AlunoDao;
import br.edu.faculdadedelta.projeto1504.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnListar;

    private MainHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MainHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            Aluno alunoSelecionado = (Aluno) intent.getSerializableExtra("alunoSelecionado");
            if (alunoSelecionado != null) {
                helper.popularFormulario(alunoSelecionado);
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
            AlunoDao dao = new AlunoDao();
            Aluno aluno = helper.popularModelo();
            if (aluno.getId() == null) {
                dao.incluir(aluno);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(aluno);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }
}
