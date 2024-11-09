package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Nota;

import java.util.ArrayList;

public class NotaDao implements IGenericDao<Nota> {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String[] colunas = {
            "id", "anoLetivo", "bimestre", "notaTrabalho",
            "notaAvaliacao", "alunoMatricula", "disciplinaId"
    };
    private String tabela = "Nota";
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
    public long insert(Nota nota) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1], nota.getAnoLetivo());
            values.put(colunas[2], nota.getBimestre());
            values.put(colunas[3], nota.getNotaTrabalho());
            values.put(colunas[4], nota.getNotaAvaliacao());
            values.put(colunas[5], nota.getAlunoMatricula());
            values.put(colunas[7], nota.getDisciplinaId());

            return dataBase.insert(tabela,null, values);

        }catch (SQLException ex){
            Log.e("NotaDao", "Erro: NotaDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Nota nota) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], nota.getAnoLetivo());
            values.put(colunas[2], nota.getBimestre());
            values.put(colunas[3], nota.getNotaTrabalho());
            values.put(colunas[4], nota.getNotaAvaliacao());
            values.put(colunas[5], nota.getAlunoMatricula());
            values.put(colunas[7], nota.getDisciplinaId());

            String[] identificador = {String.valueOf(nota.getId())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);

        }catch (SQLException ex){
            Log.e("NotaDao", "Erro: NotaDao.update" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Nota nota) {
        try{
            String[]identificador = {String.valueOf(nota.getId())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("NotaDao", "NotaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Nota getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                return new Nota(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getDouble(4),
                        cursor.getInt(5),
                        cursor.getInt(6)
                );
            }

        }catch (SQLException ex) {
            Log.e("NotaDao", "ERRO: NotaDao.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Nota> getAll() {

        ArrayList<Nota> notas = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Nota nota = new Nota(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getDouble(3),
                            cursor.getDouble(4),
                            cursor.getInt(5),
                            cursor.getInt(6)
                    );

                    notas.add(nota);
                } while (cursor.moveToNext());
            }
            Log.d("NotaDao", "Total de turmas recuperados: " + notas.size());
            return notas;

        } catch (SQLException ex) {
            Log.e("NotaDao", "ERRO: NotaDao.getAll()" + ex.getMessage());
        }
        return notas;
    }

    public ArrayList<Nota> buscarNotasPorAlunoBimestreDisciplinaEAnoLetivo(int alunoId, int disciplinaId, int anoLetivo) {
        //SQLiteDatabase db = openHelper.getReadableDatabase();
        ArrayList<Nota> listaNotas = new ArrayList<>();


        String selection = "alunoMatricula = ? AND disciplinaId = ? AND anoLetivo = ?";
        String[] selectionArgs = {String.valueOf(alunoId), String.valueOf(disciplinaId), String.valueOf(anoLetivo)};

        try {

            Cursor cursor = dataBase.query(tabela, colunas, selection, selectionArgs, null, null, null);

            if (cursor.moveToFirst()) {
                Log.d("NotaDao", "Nota encontradas para o aluno: " + cursor.getCount());

                do {
                    Nota nota = new Nota(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getDouble(3),
                            cursor.getDouble(4),
                            cursor.getInt(5),
                            cursor.getInt(6)
                    );

                    listaNotas.add(nota);
                } while (cursor.moveToNext());
            }
        }catch (SQLException e) {
            Log.e("NotaDao", "Erro ao buscar notas", e);
        }catch (Exception e) {
            Log.e("NotaDao", "Erro ao buscar notas", e);
        }
        return listaNotas;
    }



}
