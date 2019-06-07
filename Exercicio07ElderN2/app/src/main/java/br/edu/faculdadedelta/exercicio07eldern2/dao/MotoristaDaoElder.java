package br.edu.faculdadedelta.exercicio07eldern2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.faculdadedelta.exercicio07eldern2.modelo.MotoristaElder;

public class MotoristaDaoElder extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MotoristaDaoElder(Context context) {
        super(context, "Gestor", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE motorista (id INTEGER PRIMARY KEY , nome TEXT NOT NULL, cpf TEXT NOT NULL, cnh TEXT NOT NULL, datanascimento TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(MotoristaElder motorista) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosMotorista(motorista);
        db.insert("motorista", null, dados);
    }

    private ContentValues getDadosMotorista(MotoristaElder motorista) {
        ContentValues dados = new ContentValues();

        dados.put("nome", motorista.getNome());
        dados.put("cpf", motorista.getCpf());
        dados.put("cnh", motorista.getCnh());
        dados.put("datanascimento", sdf.format(motorista.getDataNascimento()));

        return dados;
    }

    public List<MotoristaElder> listar(){
        String sql = "SELECT * FROM motorista;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<MotoristaElder> listaRetorno = new ArrayList<>();


        while (cursor.moveToNext()) {
            MotoristaElder motorista = new MotoristaElder();

            motorista.setId(cursor.getLong(cursor.getColumnIndex("id")));
            motorista.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            motorista.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));
            motorista.setCnh(cursor.getString(cursor.getColumnIndex("cnh")));
            try {
                motorista.setDataNascimento(sdf.parse(cursor.getString(cursor.getColumnIndex("datanascimento"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            listaRetorno.add(motorista);
        }

        return listaRetorno;
    }

    public void remover(MotoristaElder motorista) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {motorista.getId().toString()};

        db.delete("motorista", "id = ?", params);
    }

    public void alterar(MotoristaElder motorista) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosMotorista(motorista);
        String[] params = {motorista.getId().toString()};

        db.update("motorista", dados, "id = ?", params);
    }
}
