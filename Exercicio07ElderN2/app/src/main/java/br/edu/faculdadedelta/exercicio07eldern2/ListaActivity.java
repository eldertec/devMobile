package br.edu.faculdadedelta.exercicio07eldern2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio07eldern2.adapter.MotoristaAdapterElder;
import br.edu.faculdadedelta.exercicio07eldern2.dao.MotoristaDaoElder;
import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class ListaActivity extends AppCompatActivity {

    private ListView lvListaMotoristas;
    private MotoristaDaoElder dao = new MotoristaDaoElder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lvListaMotoristas = findViewById(R.id.lvListaMotoristas);

        lvListaMotoristas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MotoristaElder motoristaSelecionado = (MotoristaElder) parent.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), PrincipalActivity.class);
                intent.putExtra("motoristaSelecionado", motoristaSelecionado);
                startActivity(intent);
            }
        });

        lvListaMotoristas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MotoristaElder motoristaSelecionado = (MotoristaElder) parent.getItemAtPosition(position);
                dao.remover(motoristaSelecionado);
                carregarLista();
                dao.close();
                return false;
            }
        });
    }

    private void carregarLista() {
        List<MotoristaElder> motoristas = dao.listar();
            dao.close();

        MotoristaAdapterElder adapter = new MotoristaAdapterElder(motoristas, this);
        lvListaMotoristas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }
}
