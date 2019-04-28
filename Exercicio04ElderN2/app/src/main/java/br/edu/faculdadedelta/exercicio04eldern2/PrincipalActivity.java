package br.edu.faculdadedelta.exercicio04eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.faculdadedelta.exercicio04eldern2.dao.LivroDaoElder;
import br.edu.faculdadedelta.exercicio04eldern2.modelo.LivroElder;

public class PrincipalActivity extends AppCompatActivity {

    private PrincipalHelper helper;

    private Button btnSalvar;
    private Button btnListar;
    private Button btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        helper = new PrincipalHelper(this);

        Intent intent = getIntent();
        if (intent != null) {
            LivroElder livroSelecionado = (LivroElder) intent.getSerializableExtra("livroSelecionado");
            if (livroSelecionado != null) {
                helper.popularFormulario(livroSelecionado);
            }
        }

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

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
    }

    private void listar() {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void salvar() {
        String msgRetorno = helper.validarCampos();

        if (msgRetorno.equals("")) {
            LivroElder livro = helper.popularModelo();
            LivroDaoElder dao = new LivroDaoElder();
            if (livro.getId() == null) {
                dao.incluir(livro);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                dao.alterar(livro);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        } else {
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }
}
