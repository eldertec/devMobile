package com.example.projetoordemservico1305;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.projetoordemservico1305.adapter.ServicoAdapter;
import com.example.projetoordemservico1305.dao.ServicoDao;
import com.example.projetoordemservico1305.modelo.Servico;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaServico;
    private ServicoDao dao = new ServicoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaServico = findViewById(R.id.lvListaServicos);

        lvListaServico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Servico servicoSelecionado = (Servico) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("servicoSelecionado", servicoSelecionado);
                startActivity(intent);
            }
        });

        lvListaServico.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Servico servicoSelecionado = (Servico) parent.getItemAtPosition(position);
                dao.remover(servicoSelecionado);
                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        ServicoAdapter adapter = new ServicoAdapter(dao.listar(), getBaseContext());
        lvListaServico.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
