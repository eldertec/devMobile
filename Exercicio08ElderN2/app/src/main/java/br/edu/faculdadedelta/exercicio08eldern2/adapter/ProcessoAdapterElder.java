package br.edu.faculdadedelta.exercicio08eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.exercicio08eldern2.R;
import br.edu.faculdadedelta.exercicio08eldern2.modelo.ProcessoElder;

public class ProcessoAdapterElder extends BaseAdapter {

    private List<ProcessoElder> listaProcessos;
    private Context context;

    public ProcessoAdapterElder(List<ProcessoElder> listaProcessos, Context context) {
        this.listaProcessos = listaProcessos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaProcessos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProcessos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaProcessos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        ProcessoElder processo = (ProcessoElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(processo.getId()));

        TextView tvNumero = (TextView) view.findViewById(R.id.tvNumero);
        tvNumero.setText(String.format("Número: %s",processo.getNumero()));

        TextView tvAssunto = (TextView) view.findViewById(R.id.tvAssunto);
        tvAssunto.setText(String.format("Assunto: %s", processo.getAssunto()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText(String.format("Valor: %s", processo.getValor()));

        TextView tvDtAutuacao = (TextView) view.findViewById(R.id.tvDtAutuacao);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tvDtAutuacao.setText(String.format("Data de Autuação: %s", sdf.format(processo.getDataAutuacao())));

        return view;
    }
}
