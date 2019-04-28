package br.edu.faculdadedelta.exercicio04eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio04eldern2.adapter.LivroAdapterElder;
import br.edu.faculdadedelta.exercicio04eldern2.dao.LivroDaoElder;
import br.edu.faculdadedelta.exercicio04eldern2.modelo.LivroElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaLivros;
    private LivroDaoElder dao = new LivroDaoElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaLivros = findViewById(R.id.lvListaLivro);
        lvListaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LivroElder livroSelecionado = (LivroElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("livroSelecionado", livroSelecionado);
                startActivity(intent);
            }
        });

        lvListaLivros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                LivroElder livroSelecionado = (LivroElder) parent.getItemAtPosition(position);
                dao.remover(livroSelecionado);
                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        LivroAdapterElder adapter = new LivroAdapterElder(dao.listar(), getBaseContext());
        lvListaLivros.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
