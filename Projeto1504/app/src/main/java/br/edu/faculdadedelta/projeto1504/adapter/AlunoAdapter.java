package br.edu.faculdadedelta.projeto1504.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.faculdadedelta.projeto1504.R;
import br.edu.faculdadedelta.projeto1504.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno> listaAlunos;
    private Context context;

    public AlunoAdapter(List<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaAlunos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAlunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaAlunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(context, R.layout.layout_item_lista, null);

        Aluno aluno = (Aluno) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText("Id: ".concat(String.valueOf(aluno.getId())));

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText("Nome: ".concat(aluno.getNome()));

        TextView tvMatricula = (TextView) view.findViewById(R.id.tvMatricula);
        tvMatricula.setText("Matricula: ".concat(aluno.getMatricula()));

        TextView tvCpf = (TextView) view.findViewById(R.id.tvCpf);
        tvCpf.setText("CPF: ".concat(aluno.getCpf()));

        return view;
    }
}
