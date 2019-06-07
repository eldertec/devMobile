package br.edu.faculdadedelta.exercicio06eldern2;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.edu.faculdadedelta.exercicio06eldern2.dao.VeiculoDaoElder;
import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private MaterialButton btnSalvar;
    private MaterialButton btnListar;
    private MaterialButton btnLimpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        if(intent != null) {
            VeiculoElder veiculoSelecionado = (VeiculoElder) intent.getSerializableExtra("veiculoSelecionado");
            if(veiculoSelecionado != null){
                helper.popularFormulario(veiculoSelecionado);
            }
        }
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        btnLimpar = findViewById(R.id.btnlimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
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

    private void salvar() {
        String msgErro = helper.validarCampos();

        if(msgErro.equals("")){
            VeiculoElder veiculo = helper.popularModelo();
            VeiculoDaoElder dao = new VeiculoDaoElder(this);
            if(veiculo.getId() == null){
                dao.inserir(veiculo);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            }else{
                dao.alterar(veiculo);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            limpar();
        }else{
            Toast.makeText(getBaseContext(), msgErro, Toast.LENGTH_LONG).show();
        }
    }

    private void listar(){
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void limpar(){
        helper.limparCampos();
    }
}
