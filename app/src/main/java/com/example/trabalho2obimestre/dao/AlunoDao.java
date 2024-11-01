package com.example.trabalho2obimestre.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trabalho2obimestre.enums.turmaEnum;
import com.example.trabalho2obimestre.model.Aluno;

import java.util.ArrayList;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;

public class AlunoDao implements IGenericDao<Aluno>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase dataBase;

    private String[] colunas = {"RA", "Nome", "Turma", "NotaTrabalho", "NotaProva", "Media", "Presenca"};

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
        openHelper = new SQLiteDataHelper(this.context, "Aluno.db", null, 1);
        dataBase = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Aluno aluno) {
        try{
            //Nome, RA e turma não devem ser inseridos, serão pre-definidos pela instituição.
            ContentValues values = new ContentValues();
            values.put("NotaTrabalho", aluno.getNotaTrabalho());
            values.put("NotaProva", aluno.getNotaProva());
            values.put("Media", aluno.getMedia());
            values.put("Presenca", aluno.getPresenca());

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
            //Nome, RA e turma não devem ser alterados, serão pre-definidos pela instituição.
            ContentValues valores = new ContentValues();
            valores.put(colunas[2], aluno.getNotaTrabalho());
            valores.put(colunas[3], aluno.getNotaProva());
            valores.put(colunas[4], aluno.getMedia());
            valores.put(colunas[5], aluno.getPresenca());

            //Criar um array com o identificador do aluno (RA):
            //Esse array é usado como argumento para saber onde (WHERE) atualizar.
            String[] identificador = {String.valueOf(aluno.getRA())};

            return dataBase.update(tabela, valores, colunas[0] + "= ?", identificador);
            //O update ocorrerá nas tabelas, irá inserir os valores, onde o RA for igual ao identificador.
        }catch(SQLException ex){
            Log.e("AlunoDao", "AlunoDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Aluno aluno) {
        try{
            String[]identificador = {String.valueOf(aluno.getRA())};

            return dataBase.delete(tabela, colunas[0] + "= ?", identificador);
        }catch(SQLException ex){
            Log.e("AlunoDao", "AlunoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public Aluno getById(long id) {
      try{
          String[]identificador = {String.valueOf(id)};
          Cursor cursor = dataBase.query(tabela, colunas, colunas[0] + "= ?", identificador, null, null, null);

          if(cursor.moveToFirst()){
              Aluno aluno = new Aluno();

              //Converter ENUM para String:
              String turmaString = cursor.getString(2);
              turmaEnum turmaEnum = com.example.trabalho2obimestre.enums.turmaEnum.valueOf(turmaString);

              aluno.setRA(cursor.getInt(0));
              aluno.setNome(cursor.getString(1));
              aluno.setTurma(cursor.getString(2));
              aluno.setNotaTrabalho(cursor.getInt(3));
              aluno.setNotaProva(cursor.getInt(4));
              aluno.setMedia(cursor.getDouble(5));
              aluno.setPresenca(cursor.getInt(6));
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
                    String turmaString = cursor.getString(2); // Obtém a turma como String
                    turmaEnum turmaEnum = com.example.trabalho2obimestre.enums.turmaEnum.valueOf(turmaString); // Converte para turmaEnum

                    aluno.setRA(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setTurma(cursor.getString(2)); // Define a turma do aluno
                    aluno.setNotaTrabalho(cursor.getInt(3));
                    aluno.setNotaProva(cursor.getInt(4));
                    aluno.setMedia(cursor.getDouble(5));
                    aluno.setPresenca(cursor.getInt(6));

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
    //Ele é criado no Helper e para chegar até o Controller, precisa do intermedio da Dao.
    public ArrayList<Aluno> buscarAlunosPorTurma(String turma) {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        //Implementar a lógica para buscar alunos da turma no banco
        //Exemplo (ajuste conforme sua lógica):
        String query = "SELECT * FROM Aluno WHERE Turma = ?";
        Cursor cursor = dataBase.rawQuery(query, new String[]{turma});

        if (cursor.moveToFirst()) {
            do {
                Aluno aluno = new Aluno();
                aluno.setRA(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));
                aluno.setTurma(cursor.getString(2));
                aluno.setNotaTrabalho(cursor.getInt(3));
                aluno.setNotaProva(cursor.getInt(4));
                aluno.setMedia(cursor.getDouble(5));
                aluno.setPresenca(cursor.getInt(6));
                listaAlunos.add(aluno);
            } while (cursor.moveToNext());
        }
        cursor.close(); // Feche o cursor após o uso
        return listaAlunos;
    }

}
