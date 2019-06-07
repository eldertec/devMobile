package br.edu.faculdadedelta.exercicio08eldern2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio08eldern2.adapter.ProcessoAdapterElder;
import br.edu.faculdadedelta.exercicio08eldern2.dao.ProcessoDaoElder;
import br.edu.faculdadedelta.exercicio08eldern2.modelo.ProcessoElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaProcessos;
    private ProcessoDaoElder dao = new ProcessoDaoElder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaProcessos = findViewById(R.id.lvListaProcessos);

        lvListaProcessos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProcessoElder processoSelecionado = (ProcessoElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), FormularioActivity.class);
                intent.putExtra("processoSelecionado", processoSelecionado);
                startActivity(intent);
            }
        });

        lvListaProcessos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProcessoElder processoSelecionado = (ProcessoElder) parent.getItemAtPosition(position);
                dao.remover(processoSelecionado);
                carregarLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        ProcessoAdapterElder adapter = new ProcessoAdapterElder(dao.listar(), getBaseContext());
        lvListaProcessos.setAdapter(adapter);
    }
}
