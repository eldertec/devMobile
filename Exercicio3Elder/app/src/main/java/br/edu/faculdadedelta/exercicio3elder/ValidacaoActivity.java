package br.edu.faculdadedelta.exercicio3elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private TextView tvProduto;
    private TextView tvValor;
    private TextView tvCliente;
    private TextView tvDtVenda;

    private String produto;
    private String valor;
    private String cliente;
    private String dataVenda;

    public static final int RESULT_SUCESS = 1;
    public static final int RESULT_ERRO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        tvProduto = findViewById(R.id.tvProduto);
        tvValor = findViewById(R.id.tvValor);
        tvCliente = findViewById(R.id.tvCliente);
        tvDtVenda = findViewById(R.id.tvDtVenda);

        Intent intent = getIntent();
        if (intent != null) {
            produto = intent.getStringExtra("produtoParam");
            tvProduto.setText("Produto: " + produto);

            valor = intent.getStringExtra("valorParam");
            tvValor.setText("Valor: " + valor);

            cliente = intent.getStringExtra("clienteParam");
            tvCliente.setText("Cliente: " + cliente);

            dataVenda = intent.getStringExtra("dtVendaParam");
            tvDtVenda.setText("Data da venda: " + dataVenda);
        }
    }

    public void validar(View view) {
        String msgRetorno = validarCampos();
        Intent intent = new Intent();

        if (msgRetorno.equals("")) {


            if (produto.length() <= 10) {
                msgRetorno = "O Produto deve ter mais que 10 caracteres!";
            }

            double valorConvertido = Double.parseDouble(valor);
            if (valorConvertido <= 658) {
                msgRetorno += "\nO Valor deve ser maior que 658!";
            }

            if (cliente.length() <= 40) {
                msgRetorno += "\nO Cliente deve ter mais que 40 caracteres!";
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataValida = sdf.parse("01/01/2014");
                Date dataConvertida = sdf.parse(dataVenda);

                if (dataConvertida.before(dataValida)) {
                    msgRetorno += "\nA Data da venda deve ser posterior a 01/01/2014!";
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


            if (msgRetorno.equals("")) {
                msgRetorno = "Dados Válidos!";
            }
            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();
        } else {
            intent.putExtra("msgRetorno", msgRetorno);

            setResult(RESULT_SUCESS, intent);
            finish();
        }
    }

    private String validarCampos() {
        String msgRetorno = "";

        if (produto.equals("")) {
            msgRetorno = "O Produto é obrigatório!";
        }
        if (valor.equals("")) {
            msgRetorno += "\nO Valor é obrigatório!";
        }
        if (cliente.equals("")) {
            msgRetorno += "\nO Cliente é obrigatório!";
        }
        if (dataVenda.equals("")) {
            msgRetorno += "\nA Data da venda é obrigatória!";
        }

        return msgRetorno;
    }
}
