package br.com.eldertec.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.eldertec.agenda.dao.AlunoDAO;
import br.com.eldertec.agenda.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista_alunos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        Button novoAluno = (Button) findViewById(R.id.lista_novo);

        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(lista);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        AlunoDAO dao = new AlunoDAO(this);

        List<Aluno> alunos = dao.buscarAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) lista.getItemAtPosition(info.position);

        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                } else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + aluno.getTelefone()));
                    startActivity(intentLigar);
                }
                return false;
            }
        });


        MenuItem itemSms = menu.add("Enviar SMS");
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:" + aluno.getTelefone()));
        itemSms.setIntent(intentSms);

        MenuItem itemMapa = menu.add("Visualizar no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + aluno.getEndereco()));
        itemMapa.setIntent(intentMapa);

        MenuItem itemSite = menu.add("Abrir site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        String site = aluno.getSite();
        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.deletar(aluno);
                dao.close();

                carregarLista();
                return false;
            }
        });
    }
}
