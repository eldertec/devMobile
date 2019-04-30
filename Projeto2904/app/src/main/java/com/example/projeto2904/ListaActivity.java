package com.example.projeto2904;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projeto2904.adapter.SeriadoAdapter;
import com.example.projeto2904.dao.SeriadoDao;
import com.example.projeto2904.modelo.Seriado;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaSeriados;
    private SeriadoDao dao = new SeriadoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaSeriados = findViewById(R.id.lvListaSeriados);
        lvListaSeriados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Seriado seriadoSelecionado = (Seriado) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("seriadoSelecionado", seriadoSelecionado);
                startActivity(intent);
            }
        });

        lvListaSeriados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Seriado seriadoSelecionado = (Seriado) parent.getItemAtPosition(position);
                dao.remover(seriadoSelecionado);
                carregarLista();

                return false;
            }
        });


    }

    private void carregarLista() {
        SeriadoAdapter adapter = new SeriadoAdapter(dao.listar(), getBaseContext());
        lvListaSeriados.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
