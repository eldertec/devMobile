package br.edu.faculdadedelta.exercicio03eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.edu.faculdadedelta.exercicio03eldern2.adapter.ProdutoAdapterElder;
import br.edu.faculdadedelta.exercicio03eldern2.dao.ProdutoDaoElder;
import br.edu.faculdadedelta.exercicio03eldern2.modelo.ProdutoElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaProdutos;
    private ProdutoDaoElder dao = new ProdutoDaoElder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaProdutos = findViewById(R.id.lvListaProdutos);

        lvListaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProdutoElder produtoSelecionado = (ProdutoElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("produtoSelecionado", produtoSelecionado);
                startActivity(intent);
            }
        });

        lvListaProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ProdutoElder produtoSelecionado = (ProdutoElder) parent.getItemAtPosition(position);
                dao.remover(produtoSelecionado);
                carregarLista();

                return false;
            }
        });
    }

    private void carregarLista() {
        ProdutoAdapterElder adapter = new ProdutoAdapterElder(dao.listar(), getBaseContext());
        lvListaProdutos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
