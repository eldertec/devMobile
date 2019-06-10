package br.edu.faculdadedelta.exercicio02eldern2;

import android.widget.EditText;

import br.edu.faculdadedelta.exercicio02eldern2.modelo.VendaElder;

public class PrincipalHelper {

    private EditText etCliente;
    private EditText etProduto;
    private EditText etValor;
    private EditText etQuantidade;

    private VendaElder venda;

    public PrincipalHelper(PrincipalActivity activity) {
        etCliente = activity.findViewById(R.id.etCliente);
        etProduto = activity.findViewById(R.id.etProduto);
        etValor = activity.findViewById(R.id.etValor);
        etQuantidade = activity.findViewById(R.id.etQuantidade);
        venda = new VendaElder();
    }

    public VendaElder popularModelo() {
        venda.setCliente(etCliente.getText().toString());
        venda.setProduto(etProduto.getText().toString());

        double valor = Double.parseDouble(etValor.getText().toString());
        venda.setValor(valor);

        int qtd = Integer.parseInt(etQuantidade.getText().toString());
        venda.setQuantidade(qtd);

        return venda;
    }

    public void popularFormulario(VendaElder venda) {
        etCliente.setText(venda.getCliente());
        etProduto.setText(venda.getProduto());
        etValor.setText(String.valueOf(venda.getValor()));
        etQuantidade.setText(String.valueOf(venda.getQuantidade()));
        this.venda = venda;
    }

    public void limparCampos() {
        etCliente.setText("");
        etProduto.setText("");
        etValor.setText("");
        etQuantidade.setText("");
        venda = new VendaElder();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etCliente.getText().toString().equals("")) {
            msgRetorno = "O Cliente é obrigatório!";
        }
        if (etProduto.getText().toString().equals("")) {
            msgRetorno += "\nO Produto é obrigatório!";
        }
        if (etValor.getText().toString().equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        } else {
            double valor = Double.parseDouble(etValor.getText().toString());
            if (valor <= 0) {
                msgRetorno += "\nO Valor deve ser maior que 0!";
            }
        }
        if (etQuantidade.getText().toString().equals("")) {
            msgRetorno += "\nA Quantidade é obrigatória!";
        }

        return msgRetorno;
    }
}
