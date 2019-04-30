package com.example.projeto2904.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.projeto2904.R;
import com.example.projeto2904.modelo.Seriado;

import java.util.List;

public class SeriadoAdapter extends BaseAdapter {

    private List<Seriado> listaSeriados;
    private Context context;

    public SeriadoAdapter(List<Seriado> listaSeriados, Context context) {
        this.listaSeriados = listaSeriados;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaSeriados.size();
    }

    @Override
    public Object getItem(int position) {
        return listaSeriados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaSeriados.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        Seriado seriado = (Seriado) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(seriado.getId()));

        TextView tvTitulo = (TextView) view.findViewById(R.id.tvTitulo);
        tvTitulo.setText("Titulo: ".concat(seriado.getTitulo()));

        TextView tvGenero = (TextView) view.findViewById(R.id.tvGenero);
        tvGenero.setText("Genero: ".concat(seriado.getGenero()));

        TextView tvTemporada = (TextView) view.findViewById(R.id.tvTemporada);
        tvTemporada.setText("Temporada: ".concat(String.valueOf(seriado.getTemporada())));

        TextView tvNota = (TextView) view.findViewById(R.id.tvNota);
        tvNota.setText("Nota: ".concat(String.valueOf(seriado.getNota())));

        TextView tvAnoLancamento = (TextView) view.findViewById(R.id.tvAnoLancamento);
        tvAnoLancamento.setText("Ano de Lançamento: ".concat(String.valueOf(seriado.getAnoLancamento())));


        return view;
    }
}
