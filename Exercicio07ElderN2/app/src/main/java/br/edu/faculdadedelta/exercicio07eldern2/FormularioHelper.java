package br.edu.faculdadedelta.exercicio07eldern2;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public MotoristaElder popularModelo() {
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

    public void popularFormulario(MotoristaElder motorista) {
        etNome.setText(motorista.getNome());
        etCpf.setText(motorista.getCpf());
        etCnh.setText(motorista.getCnh());
        etDtNascimento.setText(sdf.format(motorista.getDataNascimento()));
        this.motorista = motorista;
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (TextUtils.isEmpty(etNome.getText().toString())) {
            msgRetorno = "O Nome é obrigatório";
        }
        if (TextUtils.isEmpty(etCpf.getText().toString())) {
            msgRetorno += "\nO CPF é obrigatório";
        }
        if (TextUtils.isEmpty(etCnh.getText().toString())) {
            msgRetorno += "\nO CNH é obrigatório";
        }
        if (TextUtils.isEmpty(etDtNascimento.getText().toString())) {
            msgRetorno += "\nA Data de Nascimento é obrigatória";
        } else {
            try {
                Date dataMin = sdf.parse("02/01/1900");
                Date dataConvertida = sdf.parse(etDtNascimento.getText().toString());
                if (dataConvertida.before(dataMin) || dataConvertida.after(new Date())) {
                    msgRetorno += "\nA Data de Nascimento deve ser posterior a 01/01/1900 e anterior a data atual";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return msgRetorno;
    }
}
