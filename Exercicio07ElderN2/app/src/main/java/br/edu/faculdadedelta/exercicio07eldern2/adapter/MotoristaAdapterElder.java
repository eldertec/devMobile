package br.edu.faculdadedelta.exercicio07eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

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
        return null;
    }
}
