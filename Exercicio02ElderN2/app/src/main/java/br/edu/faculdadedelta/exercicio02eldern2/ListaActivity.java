package br.edu.faculdadedelta.exercicio02eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio02eldern2.adapter.VendaAdapterElder;
import br.edu.faculdadedelta.exercicio02eldern2.dao.VendaDaoElder;
import br.edu.faculdadedelta.exercicio02eldern2.modelo.VendaElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaVenda;
    private VendaDaoElder dao = new VendaDaoElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaVenda = findViewById(R.id.lvListaVenda);

        lvListaVenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VendaElder vendaSelecionada = (VendaElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("vendaSelecionada", vendaSelecionada);
                startActivity(intent);
            }
        });

        lvListaVenda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                VendaElder vendaSelecionada = (VendaElder) parent.getItemAtPosition(position);
                dao.remover(vendaSelecionada);
                carregarLista();
                return false;
            }
        });
    }

    private void carregarLista() {
        VendaAdapterElder adapter = new VendaAdapterElder(dao.listar(), getBaseContext());
        lvListaVenda.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
