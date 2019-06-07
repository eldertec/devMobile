package br.edu.faculdadedelta.exercicio8elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.exercicio8elder.dao.ProcessoDaoElder;
import br.edu.faculdadedelta.exercicio8elder.modelo.ProcessoElder;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private EditText etAssunto;
    private EditText etValor;
    private EditText etDtAutuacao;

    private ProcessoElder processo = new ProcessoElder();
    private ProcessoDaoElder dao = new ProcessoDaoElder();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = findViewById(R.id.etNumero);
        etAssunto = findViewById(R.id.etAssunto);
        etValor = findViewById(R.id.etValor);
        etDtAutuacao = findViewById(R.id.etDtAutuacao);

        Intent intent = getIntent();
        if(intent != null){
            ProcessoElder processoSelecionado = (ProcessoElder) intent.getSerializableExtra("processoSelecionado");
            if(processoSelecionado != null){
                popularFormulario(processoSelecionado);
            }
        }

    }

    public void limparCampos(View view){
        etNumero.setText("");
        etAssunto.setText("");
        etValor.setText("");
        etDtAutuacao.setText("");
        processo = new ProcessoElder();
    }

    public void listar(View view){
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);
        startActivity(intent);
    }

    private void popularModelo(){
        processo.setNumero(Integer.parseInt(etNumero.getText().toString()));
        processo.setAssunto(etAssunto.getText().toString());
        processo.setValor(Double.parseDouble(etValor.getText().toString()));
        try {
            processo.setDataAutuacao(sdf.parse(etDtAutuacao.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void popularFormulario(ProcessoElder processoSelecionado){
        etNumero.setText(String.valueOf(processoSelecionado.getNumero()));
        etAssunto.setText(processoSelecionado.getAssunto());
        etValor.setText(String.valueOf(processoSelecionado.getValor()));
        etDtAutuacao.setText(sdf.format(processoSelecionado.getDataAutuacao()));
        processo.setId(processoSelecionado.getId());
    }

    private String validarCamp(){
        String msgRetorno = "";

        if(etNumero.getText().toString().equals("")){
            msgRetorno = "O número é obrigatório";
        }
        if(etAssunto.getText().toString().equals("")){
            msgRetorno += "\nO assunto é obrigatório";
        }
        if(etValor.getText().toString().equals("")){
            msgRetorno += "\nO valor é obrigatório";
        }
        if(etDtAutuacao.getText().toString().equals("")){
            msgRetorno += "\nA data de autuação é obrigatória";
        }else{
            try {
                Date dataMin = sdf.parse("01/01/1900");
                Date dataConvertida = sdf.parse(etDtAutuacao.getText().toString());
                if(dataConvertida.before(dataMin) || dataConvertida.after(new Date())){
                    msgRetorno += "\nA Data deve ser posterior a 01/01/1900 e anterior a data atual";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return msgRetorno;
    }

    public void salvar(View view){
        String msgErro = validarCamp();

        if(msgErro.equals("")){
            popularModelo();
            if(processo.getId() == null){
                dao.incluir(processo);
                Toast.makeText(getBaseContext(), "Incluido com sucesso", Toast.LENGTH_LONG).show();
            }else{
                dao.alterar(processo);
                Toast.makeText(getBaseContext(), "Alterado com sucesso", Toast.LENGTH_LONG).show();
            }
            limparCampos(null);
        }else{
            Toast.makeText(getBaseContext(), msgErro, Toast.LENGTH_LONG).show();
        }
    }
}
