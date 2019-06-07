package br.edu.faculdadedelta.exercicio06eldern2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio06eldern2.modelo.VeiculoElder;

public class VeiculoDaoElder extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public VeiculoDaoElder(Context context) {
        super(context, "Frota", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE veiculo (id INTEGER PRIMARY KEY , placa TEXT NOT NULL, marca TEXT NOT NULL, modelo TEXT NOT NULL, datafabricacao TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(VeiculoElder veiculo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosVeiculo(veiculo);
        db.insert("veiculo", null, dados);
    }

    private ContentValues getDadosVeiculo(VeiculoElder veiculo) {
        ContentValues dados = new ContentValues();

        dados.put("placa", veiculo.getPlaca());
        dados.put("marca", veiculo.getMarca());
        dados.put("modelo", veiculo.getModelo());
        dados.put("datafabricacao", sdf.format(veiculo.getDataFabricacao()));

        return dados;
    }

    public void remover(VeiculoElder veiculo) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {veiculo.getId().toString()};

        db.delete("veiculo", "id = ?", params);
    }

    public void alterar(VeiculoElder veiculo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosVeiculo(veiculo);
        String[] params = {veiculo.getId().toString()};

        db.update("veiculo", dados, "id = ?", params);
    }

    public List<VeiculoElder> listar() {
        String sql = "SELECT * FROM veiculo;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<VeiculoElder> listaRetorno = new ArrayList<>();

        while (cursor.moveToNext()) {
            VeiculoElder veiculo = new VeiculoElder();

            veiculo.setId(cursor.getLong(cursor.getColumnIndex("id")));
            veiculo.setPlaca(cursor.getString(cursor.getColumnIndex("placa")));
            veiculo.setMarca(cursor.getString(cursor.getColumnIndex("marca")));
            veiculo.setModelo(cursor.getString(cursor.getColumnIndex("modelo")));
            try {
                veiculo.setDataFabricacao(sdf.parse(cursor.getString(cursor.getColumnIndex("datafabricacao"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            listaRetorno.add(veiculo);
        }

        return listaRetorno;
    }
}
