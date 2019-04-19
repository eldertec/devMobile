package br.edu.faculdadedelta.exercicio01eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio01eldern2.DAO.BemDAOElder;
import br.edu.faculdadedelta.exercicio01eldern2.adapter.BemAdapterElder;
import br.edu.faculdadedelta.exercicio01eldern2.modelo.BemElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaBem;
    private BemDAOElder dao = new BemDAOElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaBem = findViewById(R.id.lvListaBem);
        lvListaBem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BemElder bemSelecionado = (BemElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("bemSelecionado", bemSelecionado);
                startActivity(intent);
            }
        });

        lvListaBem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                BemElder bemSelecionado = (BemElder) parent.getItemAtPosition(position);
                dao.remover(bemSelecionado);
                carregarLista();
                return false;
            }
        });
    }

    private void carregarLista() {
        BemAdapterElder adapter = new BemAdapterElder(dao.listar(), getBaseContext());
        lvListaBem.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
