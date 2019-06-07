package br.edu.faculdadedelta.exercicio8elder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio8elder.adapter.ProcessoAdapterElder;
import br.edu.faculdadedelta.exercicio8elder.dao.ProcessoDaoElder;
import br.edu.faculdadedelta.exercicio8elder.modelo.ProcessoElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaProcesso;
    private ProcessoDaoElder dao = new ProcessoDaoElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaProcesso = findViewById(R.id.lvListaProcesso);

        lvListaProcesso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProcessoElder processoSelecionado = (ProcessoElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("processoSelecionado", processoSelecionado);
                startActivity(intent);
            }
        });

        lvListaProcesso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        lvListaProcesso.setAdapter(adapter);
    }
}
