package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Disciplina;

import java.util.ArrayList;

public class DisciplinaDao implements IGenericDao<Disciplina> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String[] colunas = {"id", "nome", "professorRegistro"};
    private String tabela = "Disciplina";
    private Context context;

    private static DisciplinaDao instancia;
    public static DisciplinaDao getInstancia(Context context){
        if(instancia == null){
            instancia = new DisciplinaDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }

    private DisciplinaDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Disciplina disciplina) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1], disciplina.getNome());
            values.put(colunas[2], disciplina.getRegistroProfessor());

            return dataBase.insert(tabela,null, values);

        }catch (SQLException ex){
            Log.e("DisciplinaDao", "Erro: DisciplinaDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Disciplina disciplina) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], disciplina.getNome());
            values.put(colunas[2], disciplina.getRegistroProfessor());

            String[] identificador = {String.valueOf(disciplina.getId())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);

        }catch (SQLException ex){
            Log.e("DisciplinaDao", "Erro: DisciplinaDao.update" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Disciplina disciplina) {
        try{
            String[]identificador = {String.valueOf(disciplina.getId())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("DisciplinaDao", "DisciplinaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Disciplina getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Disciplina disciplina = new Disciplina();

                disciplina.setId(cursor.getInt(0));
                disciplina.setNome(cursor.getString(1));
                //Todo: implementar o ProfessorDao
                //disciplina.setProfessor(cursor.getInt(2));

                return disciplina;
            }

        }catch (SQLException ex) {
            Log.e("DisciplinaDao", "ERRO: DisciplinaDao.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Disciplina> getAll() {

        ArrayList<Disciplina> disciplinas = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Disciplina disciplina = new Disciplina();

                    disciplina.setId(cursor.getInt(0));
                    disciplina.setNome(cursor.getString(1));

                    //Todo: Implementar o professor Dao.
                    //disciplina.setProfessor(cursor.getInt(2));

                    disciplinas.add(disciplina);
                } while (cursor.moveToNext());
            }
            Log.d("DisciplinaDao", "Total de disciplinas recuperados: " + disciplinas.size());
            return disciplinas;

        } catch (SQLException ex) {
            Log.e("DisciplinaDao", "ERRO: DisciplinaDao.getAll()" + ex.getMessage());
        }
        return disciplinas;
    }

    public ArrayList<Disciplina> buscarDisciplinasPorProfessor(int regProfessor){
        ArrayList<Disciplina> disciplinas = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Disciplina disciplina = new Disciplina();

                    disciplina.setId(cursor.getInt(0));
                    disciplina.setNome(cursor.getString(1));

                    //Todo: Implementar o professor Dao.
                    //disciplina.setProfessor(cursor.getInt(2));

                    disciplinas.add(disciplina);
                } while (cursor.moveToNext());
            }
            Log.d("DisciplinaDao", "Total de disciplinas recuperados: " + disciplinas.size());
            return disciplinas;

        } catch (SQLException ex) {
            Log.e("DisciplinaDao", "ERRO: DisciplinaDao.getAll()" + ex.getMessage());
        }
        return disciplinas;
    }
}
