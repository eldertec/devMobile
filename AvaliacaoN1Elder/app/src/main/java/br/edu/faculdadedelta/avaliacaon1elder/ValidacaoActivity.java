package br.edu.faculdadedelta.avaliacaon1elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvCliente;
    private TextView tvDtNascimento;
    private TextView tvCpf;
    private TextView tvServico;
    private TextView tvQuantidade;
    private TextView tvValor;
    private TextView tvDtInicio;
    private TextView tvDtFim;

    private String cliente;
    private String dtNascimento;
    private String cpf;
    private String servico;
    private String quantidade;
    private String valor;
    private String dtInicio;
    private String dtFim;

    private Button btnValidar;

    public static final int RESULT_SUCESS = 1;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvCliente = findViewById(R.id.tvCliente);
        tvDtNascimento = findViewById(R.id.tvDtNascimento);
        tvCpf = findViewById(R.id.tvCpf);
        tvServico = findViewById(R.id.tvServico);
        tvQuantidade = findViewById(R.id.tvQuantidade);
        tvValor = findViewById(R.id.tvValor);
        tvDtInicio = findViewById(R.id.tvDtInicio);
        tvDtFim = findViewById(R.id.tvDtFim);

        Intent intent = getIntent();
        if (intent != null) {
            cliente = intent.getStringExtra("clienteParam");
            dtNascimento = intent.getStringExtra("dtNascimentoParam");
            cpf = intent.getStringExtra("cpfParam");
            servico = intent.getStringExtra("servicoParam");
            quantidade = intent.getStringExtra("quantidadeParam");
            valor = intent.getStringExtra("valorParam");
            dtInicio = intent.getStringExtra("dtInicioParam");
            dtFim = intent.getStringExtra("dtFimParam");

            tvCliente.setText("Cliente: ".concat(cliente));
            tvDtNascimento.setText("Data de Nascimento: ".concat(dtNascimento));
            tvCpf.setText("CPF: ".concat(cpf));
            tvServico.setText("Serviço: ".concat(servico));
            tvQuantidade.setText("Quantidade: ".concat(quantidade));
            tvValor.setText("Valor: R$".concat(valor));
            tvDtInicio.setText("Data Inicial: ".concat(dtInicio));
            tvDtFim.setText("Data Final: ".concat(dtFim));
        }

        btnValidar = findViewById(R.id.btnValidar);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    private void validar() {
        String msgRetorno = validarCampos();

        if (msgRetorno.equals("")) {

            if (isClienteValid()) {
                msgRetorno = "O cliente deve ter mais de 20 caracteres";
            }

            if (isDtNascimentoValid()) {
                msgRetorno += "\nA data de nascimento deve ser posterior a 1900 e anterior a data atual";
            }

            if (isCpfValid()) {
                msgRetorno += "\nO CPF deve ter 11 caracteres";
            }

            if (isServicoValid()) {
                msgRetorno += "\nO serviço deve ter no minimo 15 caracteres";
            }

            if (isQuantidadeValid()) {
                msgRetorno += "\nA Quantidade deve ser maior que zero";
            }

            if (isValorValid()) {
                msgRetorno += "\nO Valor deve ser maior que 100";
            }

            if (isDataValid()) {
                msgRetorno += "\nA Data Inicial deve ser anterior a Data Final";
            }

            if (msgRetorno.equals("")) {
                msgRetorno = "Dados Válidos!";
            }

            Intent intent = new Intent();
            intent.putExtra("msgRetorno", msgRetorno);
            setResult(RESULT_SUCESS, intent);
            finish();

        } else {
            Intent intent = new Intent();
            intent.putExtra("msgRetorno", msgRetorno);
            setResult(RESULT_SUCESS, intent);
            finish();
        }
    }

    private String validarCampos() {
        String msgRetorno = "";

        if (cliente.equals("")) {
            msgRetorno = "O Cliente é obrigatório";
        }
        if (dtNascimento.equals("")) {
            msgRetorno += "\nA Data de Nascimento é obrigatória";
        }
        if (cpf.equals("")) {
            msgRetorno += "\nO CPF é obrigatório";
        }
        if (servico.equals("")) {
            msgRetorno += "\nO Serviço é obrigatório";
        }
        if (quantidade.equals("")) {
            msgRetorno += "\nA Quantidade é obrigatória";
        }
        if (valor.equals("")) {
            msgRetorno += "\nO Valor é obrigatório";
        }
        if (dtInicio.equals("")) {
            msgRetorno += "\nA Data Inicial é obrigatória";
        }
        if (dtFim.equals("")) {
            msgRetorno += "\nA Data Final é obrigatória";
        }

        return msgRetorno;
    }

    private boolean isClienteValid() {
        return cliente.length() <= 20;
    }

    private boolean isDtNascimentoValid() {
        try {
            Date dataMin = sdf.parse("01/01/1901");
            Date dataNascimento = sdf.parse(dtNascimento);
            return (dataNascimento.before(dataMin) || dataNascimento.after(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isCpfValid() {
        return cpf.length() != 11;
    }

    private boolean isServicoValid() {
        return servico.length() < 15;
    }

    private boolean isQuantidadeValid() {
        int qtd = Integer.parseInt(quantidade);
        return qtd < 1;
    }

    private boolean isValorValid() {
        double valorConvertido = Double.parseDouble(valor);
        return valorConvertido <= 100;
    }

    private boolean isDataValid() {
        try {
            Date inicio = sdf.parse(dtInicio);
            Date fim = sdf.parse(dtFim);
            return inicio.after(fim);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
