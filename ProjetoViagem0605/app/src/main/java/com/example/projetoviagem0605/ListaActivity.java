package com.example.projetoviagem0605;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projetoviagem0605.adapter.ViagemAdapter;
import com.example.projetoviagem0605.dao.ViagemDao;
import com.example.projetoviagem0605.modelo.Viagem;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaViagens;
    private ViagemDao dao = new ViagemDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaViagens = findViewById(R.id.lvListaViagens);

        lvListaViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Viagem viagemSelecionada = (Viagem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), FormularioActivity.class);
                intent.putExtra("viagemSelecionada", viagemSelecionada);
                startActivity(intent);
            }
        });

        lvListaViagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Viagem viagemSelecionada = (Viagem) parent.getItemAtPosition(position);
                dao.remover(viagemSelecionada);
                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista(){
        ViagemAdapter adapter = new ViagemAdapter(dao.listar(), getBaseContext());
        lvListaViagens.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
