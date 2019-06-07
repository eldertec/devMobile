package br.edu.faculdadedelta.exercicio07eldern2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.exercicio07eldern2.R;
import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class MotoristaAdapterElder extends BaseAdapter {

    private List<MotoristaElder> listaMotoristas;
    private Context context;

    public MotoristaAdapterElder(List<MotoristaElder> listaMotoristas, Context context) {
        this.listaMotoristas = listaMotoristas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaMotoristas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMotoristas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMotoristas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;
        if(view == null){
            view = inflater.inflate(R.layout.layout_item_lista, parent, false);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        MotoristaElder motorista = listaMotoristas.get(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(motorista.getId()));

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText("Nome: ".concat(motorista.getNome()));

        TextView tvCpf = (TextView) view.findViewById(R.id.tvCpf);
        tvCpf.setText("CPF: ".concat(motorista.getCpf()));

        TextView tvCnh = (TextView) view.findViewById(R.id.tvCnh);
        tvCnh.setText("CNH: ".concat(motorista.getCnh()));

        TextView tvDtnascimento = (TextView) view.findViewById(R.id.tvDtNascimento);
        tvDtnascimento.setText("Data de nascimento: ".concat(sdf.format(motorista.getDataNascimento())));

        return view;
    }
}
