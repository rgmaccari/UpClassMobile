package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Presenca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PresencaDao implements IGenericDao<Presenca> {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String colunas[] = {"id", "data", "presente", "alunoMatricula", "disciplinaId"};
    private String tabela = "Presenca";
    private Context context;
    private static PresencaDao instancia;

    private SimpleDateFormat dateFormat;

    public static PresencaDao getInstancia(Context context){
        if(instancia == null){
            instancia = new PresencaDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }

    private PresencaDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }

    @Override
    public long insert(Presenca presenca) {
        try {

            ContentValues values = new ContentValues();
            values.put(colunas[1], dateFormat.format(presenca.getData()));
            values.put(colunas[2], presenca.isPresente() ? 1 : 0); // Armazena como 1 para presente e 0 para ausente
            values.put(colunas[3], presenca.getAlunoMatricula());
            values.put(colunas[4], presenca.getDisciplinaId());

            return dataBase.insert(tabela,null, values);

        }catch (SQLException ex){
            Log.e("PresencaDao", "Erro: PresencaDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Presenca presenca) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], dateFormat.format(presenca.getData()));
            values.put(colunas[2], presenca.isPresente() ? 1 : 0); // Armazena como 1 para presente e 0 para ausente
            values.put(colunas[3], presenca.getAlunoMatricula());
            values.put(colunas[4], presenca.getDisciplinaId());

            String[] identificador = {String.valueOf(presenca.getId())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);

        }catch (SQLException ex){
            Log.e("PresencaDao", "Erro: PresencaDao.update" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Presenca presenca) {
        try{
            String[]identificador = {String.valueOf(presenca.getId())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("PresencaDao", "PresencaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Presenca getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Presenca presenca = new Presenca();

                presenca.setId(cursor.getInt(0));
                presenca.setData(dateFormat.parse(cursor.getString(1)));
                presenca.setPresente(cursor.getInt(2) == 1);
                presenca.setAlunoMatricula(cursor.getInt(3));
                presenca.setDisciplinaId(cursor.getInt(4));

                return presenca;
            }

        }catch (SQLException ex) {
            Log.e("PresencaDao", "ERRO: PresencaDaao.getById()" + ex.getMessage());
        } catch (ParseException ex) {
            Log.e("PresencaDao", "ERRO: PresencaDaao.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Presenca> getAll() {

        ArrayList<Presenca> presencas = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Presenca presenca = new Presenca();

                    presenca.setId(cursor.getInt(0));
                    presenca.setData(dateFormat.parse(cursor.getString(1)));
                    presenca.setPresente(cursor.getInt(2) == 1);
                    presenca.setAlunoMatricula(cursor.getInt(3));
                    presenca.setDisciplinaId(cursor.getInt(4));

                    presencas.add(presenca);
                } while (cursor.moveToNext());
            }
            Log.d("PresencaDao", "Total de turmas recuperados: " + presencas.size());
            return presencas;

        } catch (SQLException ex) {
            Log.e("PresencaDao", "ERRO: PresencaDao.getAll()" + ex.getMessage());
        } catch (ParseException ex) {
            Log.e("PresencaDao", "ERRO: PresencaDao.getAll()" + ex.getMessage());
        }
        return presencas;
    }

    public Presenca buscarPresencaPorDataAlunoEDisciplina(Date data, int alunoMatricula, int disciplinaId){

        try {
            String selection = "data = ? AND alunoMatricula = ? AND disciplinaId = ?";
            String[] selectionArgs = {
                    dateFormat.format(data),
                    String.valueOf(alunoMatricula),
                    String.valueOf(disciplinaId)
            };

            Cursor cursor = dataBase.query(
                    tabela,
                    colunas,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if(cursor.moveToFirst()){
                Presenca presenca = new Presenca();

                presenca.setId(cursor.getInt(0));
                presenca.setData(dateFormat.parse(cursor.getString(1)));
                presenca.setPresente(cursor.getInt(2) == 1);
                presenca.setAlunoMatricula(cursor.getInt(3));
                presenca.setDisciplinaId(cursor.getInt(4));

                return presenca;
            }
        }catch (SQLException ex){
            Log.e("PresencaDao", "ERRO: buscarPresenca.getById()" + ex.getMessage());
        } catch (ParseException ex) {
            Log.e("PresencaDao", "ERRO: buscarPresenca.getById()" + ex.getMessage());
        }

        return null;
    }
}
