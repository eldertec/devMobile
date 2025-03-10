package br.edu.faculdadedelta.exercicio01eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.faculdadedelta.exercicio01eldern2.R;
import br.edu.faculdadedelta.exercicio01eldern2.modelo.BemElder;

public class BemAdapterElder extends BaseAdapter {

    private List<BemElder> listaBens;
    private Context context;

    public BemAdapterElder(List<BemElder> listaBens, Context context) {
        this.listaBens = listaBens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaBens.size();
    }

    @Override
    public Object getItem(int position) {
        return listaBens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaBens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        BemElder bem = (BemElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(bem.getId()));

        TextView tvDescricao = (TextView) view.findViewById(R.id.tvDescricao);
        tvDescricao.setText(String.format("Descrição: %s",bem.getDescricao()));

        TextView tvEspecificacao = (TextView) view.findViewById(R.id.tvEspecificacao);
        tvEspecificacao.setText(String.format("Especificação: %s",bem.getEspecificacao()));

        TextView tvDepartamento = (TextView) view.findViewById(R.id.tvDepartamento);
        tvDepartamento.setText(String.format("Departamento: %s",bem.getDepartamento()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText(String.format("Valor: %s",bem.getValor()));

        TextView tvQuantidade = (TextView) view.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText(String.format("Quantidade: %s",bem.getQuantidade()));

        return view;
    }
}
