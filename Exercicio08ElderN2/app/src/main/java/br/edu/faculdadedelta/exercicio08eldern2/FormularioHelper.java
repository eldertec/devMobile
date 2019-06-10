package br.edu.faculdadedelta.exercicio08eldern2;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.faculdadedelta.exercicio08eldern2.modelo.ProcessoElder;

public class FormularioHelper {

    private TextInputEditText etNumero;
    private TextInputEditText etAssunto;
    private TextInputEditText etValor;
    private TextInputEditText etDtAutuacao;

    private TextInputLayout ilNumero;
    private TextInputLayout ilAssunto;
    private TextInputLayout ilValor;
    private TextInputLayout ilDtAutuacao;

    private ProcessoElder processo;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormularioHelper(FormularioActivity activity){
        etNumero = activity.findViewById(R.id.formNumero);
        etAssunto = activity.findViewById(R.id.formAssunto);
        etValor = activity.findViewById(R.id.formValor);
        etDtAutuacao = activity.findViewById(R.id.formDtAutuacao);
        ilNumero = activity.findViewById(R.id.hintNumero);
        ilAssunto = activity.findViewById(R.id.hintAssunto);
        ilValor = activity.findViewById(R.id.hintValor);
        ilDtAutuacao = activity.findViewById(R.id.hintDtAutuacao);
        processo = new ProcessoElder();
    }

    public ProcessoElder popularModelo(){
        processo.setNumero(Integer.parseInt(etNumero.getText().toString()));
        processo.setAssunto(etAssunto.getText().toString());
        processo.setValor(Double.parseDouble(etValor.getText().toString()));

        try {
            processo.setDataAutuacao(sdf.parse(etDtAutuacao.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return processo;
    }

    public void popularFormulario(ProcessoElder processo){
        etNumero.setText(String.valueOf(processo.getNumero()));
        etAssunto.setText(processo.getAssunto());
        etValor.setText(String.valueOf(processo.getValor()));
        etDtAutuacao.setText(sdf.format(processo.getDataAutuacao()));
        this.processo = processo;
    }

    public void limparCampos(){
        etAssunto.setText("");
        etValor.setText("");
        etNumero.setText("");
        etDtAutuacao.setText("");
        ilAssunto.setError(null);
        ilValor.setError(null);
        ilNumero.setError(null);
        ilDtAutuacao.setError(null);
        processo = new ProcessoElder();
    }

    public String validarCampos(){
        String msgRetorno = "";

        if(TextUtils.isEmpty(etNumero.getText().toString())){
            ilNumero.setError(" ");
            msgRetorno = "O Número é obrigatório!";
        }
        if(TextUtils.isEmpty(etAssunto.getText().toString())){
            ilAssunto.setError(" ");
            msgRetorno += "\nO Assunto é obrigatório!";
        }
        if(TextUtils.isEmpty(etValor.getText().toString())){
            ilValor.setError(" ");
            msgRetorno += "\nO Valor é obrigatório!";
        }
        if(TextUtils.isEmpty(etDtAutuacao.getText().toString())){
            ilDtAutuacao.setError(" ");
            msgRetorno += "\nA Data de Autuação é obrigatória!";
        }else{
            try {
                Date dataMin = sdf.parse("02/01/1900");
                Date dataConvertida = sdf.parse(etDtAutuacao.getText().toString());
                Date agora = new Date();
                if(dataConvertida.before(dataMin) || dataConvertida.after(agora)){
                    ilDtAutuacao.setError(" ");
                    msgRetorno += String.format("\nA Data de Autuação deve ser de %s a %s!", sdf.format(dataMin) ,sdf.format(agora));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return msgRetorno;
    }

}
