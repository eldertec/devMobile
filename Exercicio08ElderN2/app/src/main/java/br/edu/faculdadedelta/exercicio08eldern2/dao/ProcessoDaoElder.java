package br.edu.faculdadedelta.exercicio08eldern2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.exercicio08eldern2.modelo.ProcessoElder;

public class ProcessoDaoElder extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ProcessoDaoElder(Context context) {
        super(context, "Exercicio08", null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE processo (id INTEGER PRIMARY KEY , numero INTEGER NOT NULL, assunto TEXT NOT NULL, valor REAL NOT NULL, dataautuacao TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(ProcessoElder processo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosProcesso(processo);
        db.insert("processo", null, dados);
    }

    private ContentValues getDadosProcesso(ProcessoElder processo) {
        ContentValues dados = new ContentValues();

        dados.put("numero", processo.getNumero());
        dados.put("assunto", processo.getAssunto());
        dados.put("valor", processo.getValor());
        dados.put("dataautuacao", sdf.format(processo.getDataAutuacao()));

        return dados;
    }

    public List<ProcessoElder> listar() {
        String sql = "SELECT * FROM processo;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<ProcessoElder> listaRetorno = new ArrayList<>();


        while (cursor.moveToNext()) {
            ProcessoElder processo = new ProcessoElder();

            processo.setId(cursor.getLong(cursor.getColumnIndex("id")));
            processo.setNumero(cursor.getInt(cursor.getColumnIndex("numero")));
            processo.setAssunto(cursor.getString(cursor.getColumnIndex("assunto")));
            processo.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
            try {
                processo.setDataAutuacao(sdf.parse(cursor.getString(cursor.getColumnIndex("dataautuacao"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            listaRetorno.add(processo);
        }

        return listaRetorno;
    }

    public void remover(ProcessoElder processo) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {processo.getId().toString()};

        db.delete("processo", "id = ?", params);
    }

    public void alterar(ProcessoElder processo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getDadosProcesso(processo);
        String[] params = {processo.getId().toString()};

        db.update("processo", dados, "id = ?", params);
    }
}
