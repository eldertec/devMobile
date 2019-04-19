package br.edu.faculdadedelta.exercicio01eldern2.helper;

import android.widget.EditText;

import br.edu.faculdadedelta.exercicio01eldern2.PrincipalActivity;
import br.edu.faculdadedelta.exercicio01eldern2.R;
import br.edu.faculdadedelta.exercicio01eldern2.modelo.BemElder;

public class PrincipalHelper {

    private EditText etDescricao;
    private EditText etEspecificacao;
    private EditText etDepartamento;
    private EditText etValor;
    private EditText etQuantidade;

    private BemElder bem;

    public PrincipalHelper(PrincipalActivity activity) {
        etDescricao = activity.findViewById(R.id.etDescricao);
        etEspecificacao = activity.findViewById(R.id.etEspecificacao);
        etDepartamento = activity.findViewById(R.id.etDepartamento);
        etValor = activity.findViewById(R.id.etValor);
        etQuantidade = activity.findViewById(R.id.etQuantidade);
        bem = new BemElder();
    }

    public BemElder popularModelo() {
        bem.setDescricao(etDescricao.getText().toString());
        bem.setEspecificacao(etEspecificacao.getText().toString());
        bem.setDepartamento(etDepartamento.getText().toString());

        double valor = Double.parseDouble(etValor.getText().toString());
        bem.setValor(valor);

        int qtd = Integer.parseInt(etQuantidade.getText().toString());
        bem.setQuantidade(qtd);

        return bem;
    }

    public void preencherFormulario(BemElder bem) {
        etDescricao.setText(bem.getDescricao());
        etEspecificacao.setText(bem.getEspecificacao());
        etDepartamento.setText(bem.getDepartamento());
        etValor.setText(String.valueOf(bem.getValor()));
        etQuantidade.setText(String.valueOf(bem.getQuantidade()));
        this.bem = bem;
    }

    public void limparCampos() {
        etDescricao.setText("");
        etEspecificacao.setText("");
        etDepartamento.setText("");
        etValor.setText("");
        etQuantidade.setText("");
        bem = new BemElder();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etDescricao.getText().toString().equals("")) {
            msgRetorno = "A Descrição é obrigatória!";
        }
        if (etEspecificacao.getText().toString().equals("")) {
            msgRetorno += "\nA Especificação é obrigatória!";
        }
        if (etDepartamento.getText().toString().equals("")) {
            msgRetorno += "\nO Departamento é obrigatório!";
        }
        if (etValor.getText().toString().equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        } else {
            double valor = Double.parseDouble(etValor.getText().toString());
            if (valor <= 0) {
                msgRetorno += "\nO Valor deve ser maior que zero!";
            }
        }
        if (etQuantidade.getText().toString().equals("")) {
            msgRetorno += "\nA Quantidade é obrigatória!";
        }

        return msgRetorno;
    }
}
