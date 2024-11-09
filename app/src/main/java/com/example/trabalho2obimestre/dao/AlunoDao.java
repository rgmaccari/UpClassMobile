package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.model.Aluno;

import java.util.ArrayList;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;

public class AlunoDao implements IGenericDao<Aluno>{
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String[] colunas = {"matricula", "nome", "cpf", "turmaId"};
    private String tabela = "Aluno";
    private Context context;
    private static AlunoDao instancia;

    public static AlunoDao getInstancia(Context context){
        if(instancia == null){
            instancia = new AlunoDao(context);
            return instancia;
        }else{
            return instancia;
        }
    }
    private AlunoDao(Context context){
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "DB_UpClass", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Aluno aluno) {
        try{
            ContentValues values = new ContentValues();
            values.put(colunas[1], aluno.getNome());
            values.put(colunas[2], aluno.getCpf());
            values.put(colunas[3], aluno.getTurma());

            // Insere os dados na tabela e retorna o ID do novo registro
            return dataBase.insert(tabela, null, values);

        }catch(SQLException ex){
            Log.e("AlunoDao", "Erro: AlunoDao.insert()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Aluno aluno) {
        try{

            ContentValues values = new ContentValues();
            values.put(colunas[1], aluno.getNome());
            values.put(colunas[2], aluno.getCpf());
            values.put(colunas[3], aluno.getTurma());

            String[] identificador = {String.valueOf(aluno.getMatricula())};

            return dataBase.update(tabela, values, colunas[0] + "= ?", identificador);
            //O update ocorrerá nas tabelas, irá inserir os valores, onde o Matricula for igual ao identificador.
        }catch(SQLException ex){
            Log.e("AlunoDao", "AlunoDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Aluno aluno) {
        try{
            String[]identificador = {String.valueOf(aluno.getMatricula())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("AlunoDao", "AlunoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Aluno getById(long matricula) {
      try{
          String[]identificador = {String.valueOf(matricula)};
          Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

          if(cursor.moveToFirst()){
              Aluno aluno = new Aluno();

              aluno.setMatricula(cursor.getInt(0));
              aluno.setNome(cursor.getString(1));
              aluno.setCpf(cursor.getString(2));
              aluno.setTurma(cursor.getInt(3));

              return aluno;
          }
      }catch(SQLException ex){
          Log.e("AlunoDao", "ERRO: AlunoDao.getById()" + ex.getMessage());
      }
      return null;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        try {
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Aluno aluno = new Aluno();

                    aluno.setMatricula(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setCpf(cursor.getString(2));
                    aluno.setTurma(cursor.getInt(3));

                    listaAlunos.add(aluno);
                } while (cursor.moveToNext());
            }
            Log.d("AlunoDao", "Total de alunos recuperados: " + listaAlunos.size());
            return listaAlunos;

        } catch (SQLException ex) {
            Log.e("AlunoDao", "ERRO: AlunoDao.getAll()" + ex.getMessage());
        }
        return listaAlunos; // Retorna lista vazia se falhar
    }

    //Método para busca de alunos por turma:
    public ArrayList<Aluno> buscarAlunosPorTurma(int turmaId) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Cursor cursor = null;

        try {
            String[] args = {String.valueOf(turmaId)};
            cursor = dataBase.query("ALUNO", new String[]{"matricula", "nome", "cpf", "turmaId"},
                    "turmaId = ?", args, null, null, "nome");

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Aluno aluno = new Aluno();
                    aluno.setMatricula(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setCpf(cursor.getString(2));
                    aluno.setTurma(cursor.getInt(3));
                    alunos.add(aluno);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return alunos;
    }

}
