package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;


public class TurmaDao implements IGenericDao<Turma>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String colunas[] = {"id", "nomeTurma", "anoLetivo"};
    private String tabela = "Turma";
    private Context context;
    private static TurmaDao instancia;

    public static TurmaDao getInstancia(Context context){
        if(instancia == null){
            instancia = new TurmaDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }

    private TurmaDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Turma turma) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1], turma.getNomeTurma());
            values.put(colunas[2], turma.getAnoLetivo());

            return dataBase.insert(tabela,null, values);

        }catch (SQLException ex){
            Log.e("TurmaDao", "Erro: TurmaDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Turma turma) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], turma.getNomeTurma());
            values.put(colunas[2], turma.getAnoLetivo());

            String[] identificador = {String.valueOf(turma.getId())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);

        }catch (SQLException ex){
            Log.e("TurmaDao", "Erro: TurmaDao.update" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Turma turma) {
        try{
            String[]identificador = {String.valueOf(turma.getId())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("TurmaDao", "TurmaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Turma getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Turma turma = new Turma();

                turma.setId(cursor.getInt(0));
                turma.setNomeTurma(cursor.getString(1));
                turma.setAnoLetivo(cursor.getInt(2));

                return turma;
            }

        }catch (SQLException ex) {
            Log.e("TurmaDao", "ERRO: TurmaDaro.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Turma> getAll() {

        ArrayList<Turma> turmas = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Turma turma = new Turma();

                    turma.setId(cursor.getInt(0));
                    turma.setNomeTurma(cursor.getString(1));
                    turma.setAnoLetivo(cursor.getInt(2));

                    turmas.add(turma);
                } while (cursor.moveToNext());
            }
            Log.d("TurmaDao", "Total de turmas recuperados: " + turmas.size());
            return turmas;

        } catch (SQLException ex) {
            Log.e("TurmaDao", "ERRO: TurmaDao.getAll()" + ex.getMessage());
        }
        return turmas;
    }

    public ArrayList<Turma> buscarTurmasPorDisciplina(int disciplinaId) {

        ArrayList<Turma> turmas = new ArrayList<>();

        try {

            String query = "SELECT * FROM Turma t " +
                    "INNER JOIN Turma_Disciplina td ON td.turmaId = t.id " +
                    "WHERE td.disciplinaId = ?";

            Cursor cursor = dataBase.rawQuery(query, new String[]{String.valueOf(disciplinaId)});

            if (cursor.moveToFirst()) {
                do {
                    Turma turma = new Turma();

                    turma.setId(cursor.getInt(0));
                    turma.setNomeTurma(cursor.getString(1));
                    turma.setAnoLetivo(cursor.getInt(2));

                    turmas.add(turma);
                } while (cursor.moveToNext());
            }

            Log.d("TurmaDao", "Total de turmas recuperados: " + turmas.size());
            return turmas;

        } catch (SQLException ex) {
            Log.e("TurmaDao", "ERRO: TurmaDao.getAll()" + ex.getMessage());
        }
        return turmas;
    }

    public ArrayList<Turma> buscarTurmasPorDisciplinaEAnoLetivo(int disciplinaId, int anoLetivo) {

        ArrayList<Turma> turmas = new ArrayList<>();

        try {

            String query = "SELECT * FROM Turma t " +
                    "INNER JOIN Turma_Disciplina td ON td.turmaId = t.id " +
                    "WHERE td.disciplinaId = ? AND t.anoLetivo = ?";

            Cursor cursor = dataBase.rawQuery(query, new String[]{
                    String.valueOf(disciplinaId),
                    String.valueOf(anoLetivo)
            });

            if (cursor.moveToFirst()) {
                do {
                    Turma turma = new Turma();

                    turma.setId(cursor.getInt(0));
                    turma.setNomeTurma(cursor.getString(1));
                    turma.setAnoLetivo(cursor.getInt(2));

                    turmas.add(turma);
                } while (cursor.moveToNext());
            }

            Log.d("TurmaDao", "Total de turmas recuperados: " + turmas.size());
            return turmas;

        } catch (SQLException ex) {
            Log.e("TurmaDao", "ERRO: TurmaDao.getAll()" + ex.getMessage());
        }
        return turmas;
    }
}
