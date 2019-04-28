package br.edu.faculdadedelta.projeto1504;

import android.widget.EditText;

import br.edu.faculdadedelta.projeto1504.modelo.Aluno;

public class MainHelper {

    private EditText etNome;
    private EditText etMatricula;
    private EditText etCpf;

    private Aluno aluno;

    public MainHelper(MainActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etMatricula = activity.findViewById(R.id.etMatricula);
        etCpf = activity.findViewById(R.id.etCpf);
        aluno = new Aluno();
    }

    public Aluno popularModelo() {
        aluno.setNome(etNome.getText().toString());
        aluno.setMatricula(etMatricula.getText().toString());
        aluno.setCpf(etCpf.getText().toString());

        return aluno;
    }

    public void popularFormulario(Aluno aluno) {
        etNome.setText(aluno.getNome());
        etMatricula.setText(aluno.getMatricula());
        etCpf.setText(aluno.getCpf());
        this.aluno = aluno;
    }

    public void limparCampos() {
        etNome.setText("");
        etMatricula.setText("");
        etCpf.setText("");
        aluno = new Aluno();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etNome.getText().toString().equals("")) {
            msgRetorno = "O Nome é obrigatório";
        }
        if (etMatricula.getText().toString().equals("")) {
            msgRetorno += "\nA Matrícula é obrigatória";
        }
        if (etCpf.getText().toString().equals("")) {
            msgRetorno += "\nO CPF é obrigatório";
        } else if (etCpf.length() != 11) {
            msgRetorno += "\nO CPF deve ter 11 caracteres";
        }

        return msgRetorno;
    }
}
