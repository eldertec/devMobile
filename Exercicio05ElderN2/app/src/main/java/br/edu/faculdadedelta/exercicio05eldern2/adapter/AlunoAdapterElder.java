package br.edu.faculdadedelta.exercicio05eldern2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.faculdadedelta.exercicio05eldern2.R;
import br.edu.faculdadedelta.exercicio05eldern2.modelo.AlunoElder;

public class AlunoAdapterElder extends BaseAdapter {

    private List<AlunoElder> listaAlunos;
    private Context context;

    public AlunoAdapterElder(List<AlunoElder> listaAlunos, Context context) {
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

        AlunoElder aluno = (AlunoElder) getItem(position);

        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        tvId.setText(String.valueOf(aluno.getId()));

        TextView tvNome = (TextView) view.findViewById(R.id.tvNome);
        tvNome.setText("Nome: ".concat(aluno.getNome()));

        TextView tvIdade = (TextView) view.findViewById(R.id.tvIdade);
        tvIdade.setText("Idade: ".concat(String.valueOf(aluno.getIdade())));

        TextView tvDtNascimento = (TextView) view.findViewById(R.id.tvDtNascimento);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tvDtNascimento.setText("Data de Nascimento: ".concat(sdf.format(aluno.getDataNascimento())));

        TextView tvInstrucao = (TextView) view.findViewById(R.id.tvInstrucao);
        tvInstrucao.setText("Grau de Instrução: ".concat(aluno.getInstrucao()));

        return view;
    }
}
