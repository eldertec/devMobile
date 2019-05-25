package br.edu.faculdadedelta.exercicio07eldern2;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class FormularioHelper {

    private TextInputLayout ilNome;
    private TextInputLayout ilCpf;
    private TextInputLayout ilCnh;
    private TextInputLayout ilDtNascimento;

    private TextInputEditText etNome;
    private TextInputEditText etCpf;
    private TextInputEditText etCnh;
    private TextInputEditText etDtNascimento;

    private MotoristaElder motorista;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormularioHelper(PrincipalActivity activity) {
        ilNome = activity.findViewById(R.id.hintNome);
        ilCpf = activity.findViewById(R.id.hintCpf);
        ilCnh = activity.findViewById(R.id.hintCnh);
        ilDtNascimento = activity.findViewById(R.id.hintDtNascimento);
        etNome = activity.findViewById(R.id.formNome);
        etCpf = activity.findViewById(R.id.formCpf);
        etCnh = activity.findViewById(R.id.formCnh);
        etDtNascimento = activity.findViewById(R.id.formDtNascimento);
        motorista = new MotoristaElder();
    }

    public void limparCampos() {
        ilNome.setError(null);
        ilCpf.setError(null);
        ilCnh.setError(null);
        ilDtNascimento.setError(null);
        etNome.setText("");
        etCpf.setText("");
        etCnh.setText("");
        etDtNascimento.setText("");
        motorista = new MotoristaElder();
    }

    public MotoristaElder popularModelo(){
        motorista.setNome(etNome.getText().toString());
        motorista.setCpf(etCpf.getText().toString());
        motorista.setCnh(etCnh.getText().toString());
        try {
            motorista.setDataNascimento(sdf.parse(etDtNascimento.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return motorista;
    }

    public void popularFormulario(MotoristaElder motorista){
        etNome.setText(motorista.getNome());
        etCpf.setText(motorista.getCpf());
        etCnh.setText(motorista.getCnh());
        etDtNascimento.setText(sdf.format(motorista.getDataNascimento()));
        this.motorista = motorista;
    }

    
}
