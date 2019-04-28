package br.edu.faculdadedelta.exercicio04eldern2;

import android.widget.EditText;

import br.edu.faculdadedelta.exercicio04eldern2.modelo.LivroElder;

public class PrincipalHelper {

    private EditText etNome;
    private EditText etEditora;
    private EditText etValor;

    private LivroElder livro;

    public PrincipalHelper(PrincipalActivity activity) {
        etNome = activity.findViewById(R.id.etNome);
        etEditora = activity.findViewById(R.id.etEditora);
        etValor = activity.findViewById(R.id.etValor);
        livro = new LivroElder();
    }

    public LivroElder popularModelo() {
        livro.setNome(etNome.getText().toString());
        livro.setEditora(etEditora.getText().toString());
        livro.setValor(Double.parseDouble(etValor.getText().toString()));

        return livro;
    }

    public void popularFormulario(LivroElder livro) {
        etNome.setText(livro.getNome());
        etEditora.setText(livro.getEditora());
        etValor.setText(String.valueOf(livro.getValor()));
        this.livro = livro;
    }

    public void limparCampos() {
        etNome.setText("");
        etEditora.setText("");
        etValor.setText("");
        livro = new LivroElder();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etNome.getText().toString().equals("")) {
            msgRetorno = "O Nome do Livro é obrigatório!";
        }
        if (etEditora.getText().toString().equals("")) {
            msgRetorno += "\nA Editora é obrigatória!";
        }
        if (etValor.getText().toString().equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        } else {
            double valor = Double.parseDouble(etValor.getText().toString());
            if (valor <= 0) {
                msgRetorno += "\nO Valor deve ser maior que zero!";
            }
        }

        return msgRetorno;
    }
}
