package br.edu.faculdadedelta.exercicio05eldern2;

import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.faculdadedelta.exercicio05eldern2.modelo.AlunoElder;

public class PrincipalHelper {

    private EditText etNome;
    private EditText etIdade;
    private EditText etDtNascimento;
    private EditText etInstrucao;

    private AlunoElder aluno;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PrincipalHelper(PrincipalActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etIdade = activity.findViewById(R.id.etIdade);
        etDtNascimento = activity.findViewById(R.id.etDtNascimento);
        etInstrucao = activity.findViewById(R.id.etInstrucao);
        aluno = new AlunoElder();
    }

    public AlunoElder popularModelo() {
        aluno.setNome(etNome.getText().toString());
        aluno.setIdade(Integer.parseInt(etIdade.getText().toString()));
        try {
            aluno.setDataNascimento(sdf.parse(etDtNascimento.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aluno.setInstrucao(etInstrucao.getText().toString());

        return aluno;
    }

    public void preencherFormulario(AlunoElder aluno) {
        etNome.setText(aluno.getNome());
        etIdade.setText(String.valueOf(aluno.getIdade()));
        etDtNascimento.setText(sdf.format(aluno.getDataNascimento()));
        etInstrucao.setText(aluno.getInstrucao());

        this.aluno = aluno;
    }

    public void limparCampos() {
        etNome.setText("");
        etIdade.setText("");
        etDtNascimento.setText("");
        etInstrucao.setText("");
        aluno = new AlunoElder();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etNome.getText().toString().equals("")) {
            msgRetorno = "O Nome é obrigatório!";
        }
        if (etIdade.getText().toString().equals("")) {
            msgRetorno += "\nA Idade é obrigatória!";
        } else {
            int idade = Integer.parseInt(etIdade.getText().toString());
            if (idade <= 0) {
                msgRetorno += "\nA Idade deve ser maior que zero!";
            }
        }
        if (etDtNascimento.getText().toString().equals("")) {
            msgRetorno += "\nA Data de Nascimento é obrigatória!";
        }
        if (etInstrucao.getText().toString().equals("")) {
            msgRetorno += "\nO Grau de Instrução é obrigatório!";
        }

        return msgRetorno;
    }
}
