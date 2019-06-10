package br.edu.faculdadedelta.exercicio02eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.faculdadedelta.exercicio02eldern2.R;
import br.edu.faculdadedelta.exercicio02eldern2.modelo.VendaElder;

public class VendaAdapterElder extends BaseAdapter {

    private List<VendaElder> listaVendas;
    private Context context;

    public VendaAdapterElder(List<VendaElder> listaVendas, Context context) {
        this.listaVendas = listaVendas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaVendas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVendas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaVendas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        VendaElder venda = (VendaElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(venda.getId()));

        TextView tvCliente = (TextView) view.findViewById(R.id.tvCliente);
        tvCliente.setText(String.format("Cliente: %s",venda.getCliente()));

        TextView tvProduto = (TextView) view.findViewById(R.id.tvProduto);
        tvProduto.setText(String.format("Produto: %s",venda.getProduto()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText(String.format("Valor: %s",venda.getValor()));

        TextView tvQuantidade = (TextView) view.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText(String.format("Quantidade: %s",venda.getQuantidade()));

        return view;
    }
}
