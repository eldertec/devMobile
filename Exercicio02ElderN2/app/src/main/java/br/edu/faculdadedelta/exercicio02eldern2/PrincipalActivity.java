package br.edu.faculdadedelta.exercicio02eldern2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.faculdadedelta.exercicio02eldern2.dao.VendaDaoElder;
import br.edu.faculdadedelta.exercicio02eldern2.modelo.VendaElder;

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

        Intent intent = getIntent();
        if(intent != null){
            VendaElder vendaSelecionada = (VendaElder) intent.getSerializableExtra("vendaSelecionada");
            if(vendaSelecionada != null){
                helper.popularFormulario(vendaSelecionada);
            }
        }
    }

    private void listar() {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void salvar(){
        String msgRetorno = helper.validarCampos();

        if(msgRetorno.equals("")){
            VendaElder venda = helper.popularModelo();
            VendaDaoElder dao = new VendaDaoElder();
            if(venda.getId() == null){
                dao.incluir(venda);
                Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
            }else{
                dao.alterar(venda);
                Toast.makeText(getBaseContext(), "Alterado com sucesso!", Toast.LENGTH_LONG).show();
            }
            helper.limparCampos();
        }else{
            Toast.makeText(getBaseContext(), msgRetorno, Toast.LENGTH_LONG).show();
        }
    }
}
