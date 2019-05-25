package br.edu.faculdadedelta.exercicio06eldern2.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class VeiculoDaoElder {


    private FirebaseFirestore db;

    public VeiculoDaoElder(FirebaseFirestore db){
        this.db = db;
    }



    public void incluir(VeiculoElder veiculo) {
        Map<String, Object> inserir = new HashMap<>();

        inserir.put("placa", veiculo.getPlaca());
        inserir.put("marca", veiculo.getMarca());
        inserir.put("modelo", veiculo.getModelo());
        inserir.put("data_fabricacao", veiculo.getDataFabricacao());

        db.collection("veiculos").add(inserir).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("DONE", "DocumentSnapshot added with ID: " + documentReference.getId());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("ERRO", "Error adding document", e);

            }
        });
    }


}
