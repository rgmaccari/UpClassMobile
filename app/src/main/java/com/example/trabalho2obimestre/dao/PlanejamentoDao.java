package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Planejamento;
import com.example.trabalho2obimestre.model.Presenca;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class PlanejamentoDao implements IGenericDao<Planejamento>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String colunas[] = {"id", "descricao", "feito", "disciplinaId", "turmaId"};
    private String tabela = "Planejamento";
    private Context context;
    private static PlanejamentoDao instancia;

    public static PlanejamentoDao getInstancia(Context context){
        if(instancia == null){
            instancia = new PlanejamentoDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }

    private PlanejamentoDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Planejamento planejamento) {
        try {
            ContentValues values = new ContentValues();
            values.put(colunas[1], planejamento.getDescricao());
            values.put(colunas[2], planejamento.isCompleto() ? 1 : 0);
            values.put(colunas[3], planejamento.getDisciplinaId());
            values.put(colunas[4], planejamento.getTurmaId());

            return dataBase.insert(tabela,null, values);

        }catch (SQLException ex){
            Log.e("PlanejamentoDao", "Erro: PlanejamentoDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Planejamento planejamento) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], planejamento.getDescricao());
            values.put(colunas[2], planejamento.isCompleto() ? 1 : 0);

            String[] identificador = {String.valueOf(planejamento.getId())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);

        }catch (SQLException ex){
            Log.e("PlanejamentoDao", "Erro: PlanejamentoDao.update" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Planejamento planejamento) {
        try{
            String[]identificador = {String.valueOf(planejamento.getId())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("PlanejamentoDao", "PlanejamentoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Planejamento getById(long id) {
        try {
            String[] identificador = {String.valueOf(id)};
            Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

            if(cursor.moveToFirst()){
                Planejamento planejamento = new Planejamento();

                planejamento.setId(cursor.getInt(0));
                planejamento.setDescricao(cursor.getString(1));
                planejamento.setCompleto(cursor.getInt(2) == 1);
                planejamento.setDisciplinaId(cursor.getInt(3));
                planejamento.setTurmaId(cursor.getInt(4));

                return planejamento;
            }

        }catch (SQLException ex) {
            Log.e("PlanejamentoDao", "ERRO: PlanejamentoDao.getById()" + ex.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Planejamento> getAll() {

        ArrayList<Planejamento> planejamentos = new ArrayList<>();

        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, colunas[1]);

            if (cursor.moveToFirst()) {
                do {
                    Planejamento planejamento = new Planejamento();

                    planejamento.setId(cursor.getInt(0));
                    planejamento.setDescricao(cursor.getString(1));
                    planejamento.setCompleto(cursor.getInt(2) == 1);
                    planejamento.setDisciplinaId(cursor.getInt(3));
                    planejamento.setTurmaId(cursor.getInt(4));

                    planejamentos.add(planejamento);
                } while (cursor.moveToNext());
            }
            Log.d("PlanejamentoDao", "Total de planejamentos recuperados: " + planejamentos.size());
            return planejamentos;

        } catch (SQLException ex) {
            Log.e("TurmaDao", "ERRO: TurmaDao.getAll()" + ex.getMessage());
        }
        return planejamentos;
    }

    public ArrayList<Planejamento>
        buscaPlanejamentosPorTumaEDisciplina(int disciplinaId, int turmaId){

        ArrayList<Planejamento> planejamentos = new ArrayList<>();

        try {
            String selection = "disciplinaId = ? AND turmaId = ?";
            String[] selectionArgs = {
                    String.valueOf(disciplinaId),
                    String.valueOf(turmaId)
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

            if(cursor.moveToFirst()) {
                Planejamento planejamento = new Planejamento();

                planejamento.setId(cursor.getInt(0));
                planejamento.setDescricao(cursor.getString(1));
                planejamento.setCompleto(cursor.getInt(2) == 1);
                planejamento.setDisciplinaId(cursor.getInt(3));
                planejamento.setTurmaId(cursor.getInt(4));

                planejamentos.add(planejamento);
            }

            return planejamentos;
        }catch (SQLException ex){
            Log.e("PlanejamentoDao", "ERRO: PlanejamentoDao.buscaPlanejamentosPorTumaEDisciplina()" + ex.getMessage());
        }

        return null;
    }

}
