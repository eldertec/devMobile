package com.example.projetoordemservico1305;

import android.widget.EditText;

import com.example.projetoordemservico1305.modelo.Servico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrincipalHelper {

    private EditText etCliente;
    private EditText etServico;
    private EditText etQuantidade;
    private EditText etValor;
    private EditText etDtExecucao;

    private Servico servico;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public PrincipalHelper(PrincipalActivity activity) {
        etCliente = activity.findViewById(R.id.etCliente);
        etServico = activity.findViewById(R.id.etServico);
        etDtExecucao = activity.findViewById(R.id.etDtExecucao);
        etValor = activity.findViewById(R.id.etValor);
        etQuantidade = activity.findViewById(R.id.etQuantidade);
        servico = new Servico();
    }

    public Servico popularModelo() {
        servico.setCliente(etCliente.getText().toString());
        servico.setServico(etServico.getText().toString());

        double valor = Double.parseDouble(etValor.getText().toString());
        servico.setValor(valor);

        int qtd = Integer.parseInt(etQuantidade.getText().toString());
        servico.setQuantidade(qtd);

        try {
            servico.setDataExecucao(sdf.parse(etDtExecucao.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return servico;
    }

    public void preencherFormulario(Servico servico) {
        etCliente.setText(servico.getCliente());
        etServico.setText(servico.getServico());
        etDtExecucao.setText(sdf.format(servico.getDataExecucao()));
        etValor.setText(String.valueOf(servico.getValor()));
        etQuantidade.setText(String.valueOf(servico.getQuantidade()));
        this.servico = servico;
    }

    public void limparCampos() {
        etCliente.setText("");
        etServico.setText("");
        etDtExecucao.setText("");
        etValor.setText("");
        etQuantidade.setText("");
        servico = new Servico();
    }

    public String validarCampos() {
        String msgRetorno = "";

        if (etCliente.getText().toString().equals("")) {
            msgRetorno = "O Cliente é obrigatório!";
        }
        if (etServico.getText().toString().equals("")) {
            msgRetorno += "\nO Serviço é obrigatório!";
        }
        if (etDtExecucao.getText().toString().equals("")) {
            msgRetorno += "\nA Data de Execução é obrigatória!";
        } else {
            try {
                Date dataMin = sdf.parse("01/01/1901");
                Date dataExecucao = sdf.parse(etDtExecucao.getText().toString());
                if (dataExecucao.before(dataMin) || dataExecucao.after(new Date())) {
                    msgRetorno += "\nA Date de execução deve ser posterior a 1900 e anterior a data atual";
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (etValor.getText().toString().equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        }
        if (etQuantidade.getText().toString().equals("")) {
            msgRetorno += "\nA Quantidade é obrigatória!";
        } else {
            int qtd = Integer.parseInt(etQuantidade.getText().toString());
            if (qtd < 1) {
                msgRetorno += "\nA Quantidade deve ser maior que zero!";
            }
        }

        return msgRetorno;
    }
}
