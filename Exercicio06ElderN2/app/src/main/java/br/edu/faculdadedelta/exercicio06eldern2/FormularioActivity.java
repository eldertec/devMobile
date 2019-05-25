package br.edu.faculdadedelta.exercicio06eldern2;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.faculdadedelta.exercicio06eldern2.dao.VeiculoDaoElder;
import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class FormularioActivity extends AppCompatActivity {



    private FormularioHelper helper;

    private MaterialButton btnSalvar;

    private FirebaseFirestore db;
    private FirebaseApp firebaseApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

         firebaseApp = FirebaseApp.initializeApp(getBaseContext());

        db = FirebaseFirestore.getInstance(firebaseApp);


        helper = new FormularioHelper(this);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar() {
        VeiculoElder veiculo = helper.popularModelo();
        VeiculoDaoElder dao = new VeiculoDaoElder(db);
        dao.incluir(veiculo);
        Toast.makeText(getBaseContext(), "Incluido com sucesso!", Toast.LENGTH_LONG).show();
    }
}
