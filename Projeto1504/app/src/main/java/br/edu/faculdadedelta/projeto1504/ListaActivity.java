package br.edu.faculdadedelta.projeto1504;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.projeto1504.adapter.AlunoAdapter;
import br.edu.faculdadedelta.projeto1504.dao.AlunoDao;
import br.edu.faculdadedelta.projeto1504.modelo.Aluno;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaAlunos;
    private AlunoDao dao = new AlunoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaAlunos = findViewById(R.id.lvListaAluno);

        lvListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("alunoSelecionado", alunoSelecionado);
                startActivity(intent);
            }
        });

        lvListaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                dao.remover(alunoSelecionado);
                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        AlunoAdapter adapter = new AlunoAdapter(dao.listar(), getBaseContext());
        lvListaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
