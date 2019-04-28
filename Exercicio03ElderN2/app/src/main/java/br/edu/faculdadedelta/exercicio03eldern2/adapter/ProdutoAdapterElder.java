package br.edu.faculdadedelta.exercicio03eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.faculdadedelta.exercicio03eldern2.R;
import br.edu.faculdadedelta.exercicio03eldern2.modelo.ProdutoElder;

public class ProdutoAdapterElder extends BaseAdapter {

    private List<ProdutoElder> listaprodutos;
    private Context context;

    public ProdutoAdapterElder(List<ProdutoElder> listaprodutos, Context context) {
        this.listaprodutos = listaprodutos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaprodutos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaprodutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaprodutos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        ProdutoElder produto = (ProdutoElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText("Id: ".concat(String.valueOf(produto.getId())));

        TextView tvProduto = (TextView) view.findViewById(R.id.tvProduto);
        tvProduto.setText("Produto: ".concat(produto.getProduto()));

        TextView tvFornecedor = (TextView) view.findViewById(R.id.tvFornecedor);
        tvFornecedor.setText("Fornecedor: ".concat(produto.getFornecedor()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText("Valor: ".concat(String.valueOf(produto.getValor())));

        return view;
    }
}
