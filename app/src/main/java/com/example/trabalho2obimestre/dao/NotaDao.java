package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Notas;

import java.util.ArrayList;

public class NotaDao implements IGenericDao<Notas>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String[] colunas = {"id", "anoLetivo", "bimestre", "notaAvaliacao", "notaTrabalho"};
    private String tabela = "Notas";
    private Context context;

    private static NotaDao instancia;

    public static NotaDao getInstancia(Context context) {
        if (instancia == null) {
            instancia = new NotaDao(context);
            return instancia;
        } else {
            return instancia;
        }
    }

    private NotaDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Notas obj) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1], obj.getAnoLetivo());
            values.put(colunas[2], obj.getBimestre());
            values.put(colunas[3], obj.getNotaAvaliacao());
            values.put(colunas[4], obj.getNotaTrabalho());


            return dataBase.insert(tabela, null, values);
        } catch (Exception ex) {
            Log.e("NotaDao", "Erro: NotaDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Notas obj) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], obj.getAnoLetivo());
            values.put(colunas[2], obj.getBimestre());
            values.put(colunas[3], obj.getNotaAvaliacao());
            values.put(colunas[4], obj.getNotaTrabalho());
        }catch (Exception ex){
            Log.e("NotaDao", "Erro: NotaDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Notas obj) {
        try{
            String[] identificador = {String.valueOf(obj.getId())};
            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(Exception ex){
            Log.e("NotaDao", "Erro: NotaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Notas getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);
            if (cursor.moveToFirst()) {
                Notas nota = new Notas();
                nota.setId(cursor.getInt(0));
                nota.setAnoLetivo(cursor.getInt(1));
                nota.setBimestre(cursor.getString(2));
                nota.setNotaAvaliacao(cursor.getDouble(3));
                nota.setNotaTrabalho(cursor.getDouble(4));

                return nota;
            }
        }catch(SQLException ex){
            Log.e("NotaDao", "Erro: NotaDao.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Notas> getAll() {
        ArrayList<Notas> notas = new ArrayList<>();
        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);
            if (cursor.moveToFirst()) {
                do {
                    Notas nota = new Notas();
                    nota.setId(cursor.getInt(0));
                    nota.setAnoLetivo(cursor.getInt(1));
                    nota.setBimestre(cursor.getString(2));
                    nota.setNotaAvaliacao(cursor.getDouble(3));
                    nota.setNotaTrabalho(cursor.getDouble(4));
                    notas.add(nota);
                } while (cursor.moveToNext());
            }Log.d("NotaDao", "Total de notas recuperadas: " + notas.size());
            return notas;
        }catch(SQLException ex){
            Log.e("NotaDao", "Erro: NotaDao.getAll()" + ex.getMessage());
        }return notas;
    }

    public ArrayList<Notas> buscarNotasPorAluno(int alunoId) {
        ArrayList<Notas> notas = new ArrayList<>();
        Cursor cursor = null;

        try {
            String[] args = {String.valueOf(alunoId)};
            cursor = dataBase.query(tabela, colunas, "alunoId = ?", args, null, null, colunas[2]);

            if (cursor.moveToFirst()) {
                do {
                    Notas nota = new Notas();
                    nota.setId(cursor.getInt(0));
                    nota.setAnoLetivo(cursor.getInt(1));
                    nota.setBimestre(cursor.getString(2));
                    nota.setNotaAvaliacao(cursor.getDouble(3));
                    nota.setNotaTrabalho(cursor.getDouble(4));

                    notas.add(nota);
                } while (cursor.moveToNext());
            }Log.d("NotaDao", "Total de notas encontradas para aluno " + alunoId + ": " + notas.size());
        }catch (SQLException ex) {
            Log.e("NotaDao", "Erro: NotaDao.buscarNotasPorAluno()" + ex.getMessage());
        }finally {
            if(cursor != null) {
                cursor.close();
            }
        }

        return notas;
    }

}
