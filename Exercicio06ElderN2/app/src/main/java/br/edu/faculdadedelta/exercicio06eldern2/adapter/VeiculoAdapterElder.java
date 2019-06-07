package br.edu.faculdadedelta.exercicio06eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.exercicio06eldern2.R;
import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class VeiculoAdapterElder extends BaseAdapter {

    private List<VeiculoElder> listaVeiculos;
    private Context context;

    public VeiculoAdapterElder(List<VeiculoElder> listaVeiculos, Context context) {
        this.listaVeiculos = listaVeiculos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaVeiculos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVeiculos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaVeiculos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);
        VeiculoElder veiculo = (VeiculoElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(veiculo.getId()));

        TextView tvPlaca = (TextView) view.findViewById(R.id.tvPlaca);
        tvPlaca.setText(String.format("Placa: %s",veiculo.getPlaca()));

        TextView tvMarca = (TextView) view.findViewById(R.id.tvMarca);
        tvMarca.setText(String.format("Marca: %s", veiculo.getMarca()));

        TextView tvModelo = (TextView) view.findViewById(R.id.tvModelo);
        tvModelo.setText(String.format("Modelo: %s", veiculo.getModelo()));

        TextView tvDtfabricacao = (TextView) view.findViewById(R.id.tvDtFabricacao);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tvDtfabricacao.setText(String.format("Data de fabricação: %s", sdf.format(veiculo.getDataFabricacao())));

        return view;
    }
}
