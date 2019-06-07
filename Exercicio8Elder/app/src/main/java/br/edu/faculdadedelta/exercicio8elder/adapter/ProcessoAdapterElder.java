package br.edu.faculdadedelta.exercicio8elder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.exercicio8elder.R;
import br.edu.faculdadedelta.exercicio8elder.modelo.ProcessoElder;

public class ProcessoAdapterElder extends BaseAdapter {

    private List<ProcessoElder> listaProcesso;
    private Context context;

    public ProcessoAdapterElder(List<ProcessoElder> listaProcesso, Context context) {
        this.listaProcesso = listaProcesso;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaProcesso.size();
    }

    @Override
    public Object getItem(int position) {
        return listaProcesso.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaProcesso.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);
        ProcessoElder processo = (ProcessoElder) getItem(position);

        TextView tvId = view.findViewById(R.id.tvId);
        tvId.setText("Id: " + processo.getId());

        TextView tvNumero = view.findViewById(R.id.tvNumero);
        tvNumero.setText("Número: " + processo.getNumero());

        TextView tvAssunto = view.findViewById(R.id.tvAssunto);
        tvAssunto.setText("Assunto: " + processo.getAssunto());

        TextView tvValor = view.findViewById(R.id.tvValor);
        tvValor.setText("Valor: " + processo.getValor());

        TextView tvDtAutuacao = view.findViewById(R.id.tvDtAutuacao);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tvDtAutuacao.setText(sdf.format(processo.getDataAutuacao()));

        return view;
    }
}
