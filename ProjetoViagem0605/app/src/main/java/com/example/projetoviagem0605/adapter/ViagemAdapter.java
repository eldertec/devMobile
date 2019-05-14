package com.example.projetoviagem0605.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projetoviagem0605.R;
import com.example.projetoviagem0605.modelo.Viagem;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViagemAdapter extends BaseAdapter {

    private List<Viagem> listaViagens;
    private Context context;

    public ViagemAdapter(List<Viagem> listaViagens, Context context) {
        this.listaViagens = listaViagens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaViagens.size();
    }

    @Override
    public Object getItem(int position) {
        return listaViagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaViagens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        Viagem viagem = (Viagem) getItem(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(viagem.getId()));

        TextView tvDestino = (TextView) view.findViewById(R.id.tvDestino);
        tvDestino.setText("Destino: ".concat(viagem.getDestino()));

        TextView tvDtSaida = (TextView) view.findViewById(R.id.tvDtSaida);
        tvDtSaida.setText("Saída: ".concat(sdf.format(viagem.getDataSaida())));

        TextView tvDtChegada = (TextView) view.findViewById(R.id.tvDtChegada);
        tvDtChegada.setText("Chegada: ".concat(sdf.format(viagem.getDataChegada())));

        TextView tvItinerario = (TextView) view.findViewById(R.id.tvItinerario);
        tvItinerario.setText("Itinerário: ".concat(viagem.getItinerario()));

        TextView tvFuncionario = (TextView) view.findViewById(R.id.tvFuncionario);
        tvFuncionario.setText("Funcionário: ".concat(viagem.getFuncionario()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText("Valor: ".concat(String.valueOf(viagem.getValor())));

        return view;
    }
}
