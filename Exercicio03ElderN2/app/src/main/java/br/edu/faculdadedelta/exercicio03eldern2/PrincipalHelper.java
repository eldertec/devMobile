package br.edu.faculdadedelta.exercicio03eldern2;

import android.widget.EditText;

import br.edu.faculdadedelta.exercicio03eldern2.modelo.ProdutoElder;

public class PrincipalHelper {

    private EditText etProduto;
    private EditText etFornecedor;
    private EditText etValor;

    private ProdutoElder produto;

    public PrincipalHelper(PrincipalActivity activity) {
        etProduto = activity.findViewById(R.id.etProduto);
        etFornecedor = activity.findViewById(R.id.etFornecedor);
        etValor = activity.findViewById(R.id.etValor);
        produto = new ProdutoElder();
    }

    public ProdutoElder popularModelo() {
        produto.setProduto(etProduto.getText().toString());
        produto.setFornecedor(etFornecedor.getText().toString());

        double valor = Double.parseDouble(etValor.getText().toString());
        produto.setValor(valor);

        return produto;
    }

    public void popularFormulario(ProdutoElder produto) {
        etProduto.setText(produto.getProduto());
        etFornecedor.setText(produto.getFornecedor());
        etValor.setText(String.valueOf(produto.getValor()));
        this.produto = produto;
    }

    public void limparCampos() {
        etProduto.setText("");
        etFornecedor.setText("");
        etValor.setText("");
        produto = new ProdutoElder();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etProduto.getText().toString().equals("")) {
            msgRetorno = "O Produto é obrigatório!";
        }
        if (etFornecedor.getText().toString().equals("")) {
            msgRetorno += "\nO Fornecedor é obrigatório!";
        }
        if (etValor.getText().toString().equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        } else {
            double valor = Double.parseDouble(etValor.getText().toString());
            if (valor <= 100) {
                msgRetorno += "\nO Valor deve ser maior que 100!";
            }
        }

        return msgRetorno;
    }
}
