package br.edu.faculdadedelta.exercicio05eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio05eldern2.adapter.AlunoAdapterElder;
import br.edu.faculdadedelta.exercicio05eldern2.dao.AlunoDaoElder;
import br.edu.faculdadedelta.exercicio05eldern2.modelo.AlunoElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaAlunos;
    private AlunoDaoElder dao = new AlunoDaoElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaAlunos = findViewById(R.id.lvListaAluno);

        lvListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlunoElder alunoSelecionado = (AlunoElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("alunoSelecionado", alunoSelecionado);
                startActivity(intent);
            }
        });

        lvListaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlunoElder alunoSelecionado = (AlunoElder) parent.getItemAtPosition(position);
                dao.remover(alunoSelecionado);
                carregarLista();
                return false;
            }
        });
    }

    private void carregarLista() {
        AlunoAdapterElder adapterElder = new AlunoAdapterElder(dao.listar(), getBaseContext());
        lvListaAlunos.setAdapter(adapterElder);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
