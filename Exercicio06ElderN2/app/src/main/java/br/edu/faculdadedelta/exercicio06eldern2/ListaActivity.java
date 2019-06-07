package br.edu.faculdadedelta.exercicio06eldern2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio06eldern2.adapter.VeiculoAdapterElder;
import br.edu.faculdadedelta.exercicio06eldern2.dao.VeiculoDaoElder;
import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaVeiculos;
    private VeiculoDaoElder dao = new VeiculoDaoElder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaVeiculos = findViewById(R.id.lvListaVeiculos);

        lvListaVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VeiculoElder veiculoSelecionado = (VeiculoElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), FormularioActivity.class);
                intent.putExtra("veiculoSelecionado", veiculoSelecionado);
                startActivity(intent);
            }
        });

        lvListaVeiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                VeiculoElder veiculoSelecionado = (VeiculoElder) parent.getItemAtPosition(position);
                dao.remover(veiculoSelecionado);
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
        VeiculoAdapterElder adapter = new VeiculoAdapterElder(dao.listar(), getBaseContext());
        lvListaVeiculos.setAdapter(adapter);
    }
}
