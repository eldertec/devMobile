package com.example.projetoordemservico1305.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projetoordemservico1305.R;
import com.example.projetoordemservico1305.modelo.Servico;

import java.text.SimpleDateFormat;
import java.util.List;

public class ServicoAdapter extends BaseAdapter {

    private List<Servico> listaServicos;
    private Context context;

    public ServicoAdapter(List<Servico> listaServicos, Context context) {
        this.listaServicos = listaServicos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaServicos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaServicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaServicos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);
        Servico servico = (Servico) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText("Id: ".concat(String.valueOf(servico.getId())));

        TextView tvCliente = (TextView) view.findViewById(R.id.tvCliente);
        tvCliente.setText("Cliente: ".concat(servico.getCliente()));

        TextView tvServico = (TextView) view.findViewById(R.id.tvServico);
        tvServico.setText("Servico: ".concat(servico.getServico()));

        TextView tvQuantidade = (TextView) view.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText("Quantidade: ".concat(String.valueOf(servico.getQuantidade())));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText("Valor: ".concat(String.valueOf(servico.getValor())));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        TextView tvDtExecucao = (TextView) view.findViewById(R.id.tvDataExecucao);
        tvDtExecucao.setText("Data de Execução: ".concat(sdf.format(servico.getDataExecucao())));

        TextView tvDesconto = (TextView) view.findViewById(R.id.tvDesconto);
        tvDesconto.setText("Desconto: ".concat(String.valueOf(servico.getDesconto())));

        TextView tvValorTotal = (TextView) view.findViewById(R.id.tvValorTotal);
        tvValorTotal.setText("Valor Total: ".concat(String.valueOf(servico.getValorTotal())));



        return view;
    }
}
