package br.edu.faculdadedelta.exercicio04eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.faculdadedelta.exercicio04eldern2.R;
import br.edu.faculdadedelta.exercicio04eldern2.modelo.LivroElder;

public class LivroAdapterElder extends BaseAdapter {

    private List<LivroElder> listaLivros;
    private Context context;

    public LivroAdapterElder(List<LivroElder> listaLivros, Context context) {
        this.listaLivros = listaLivros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaLivros.size();
    }

    @Override
    public Object getItem(int position) {
        return listaLivros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaLivros.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        LivroElder livro = (LivroElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText("Id: ".concat(String.valueOf(livro.getId())));

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText("Nome: ".concat(livro.getNome()));

        TextView tvEditora = (TextView) view.findViewById(R.id.tvEditora);
        tvEditora.setText("Editora: ".concat(livro.getEditora()));

        TextView tvValor = (TextView) view.findViewById(R.id.tvValor);
        tvValor.setText("Valor: ".concat(String.valueOf(livro.getValor())));

        return view;
    }
}
