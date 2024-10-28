package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helper.SQLiteDataHelper;
import model.Aluno;

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
            values.put("Presenca", aluno.isPresenca() ? 1 : 0); // Converte boolean para int (1 ou 0)

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
            valores.put(colunas[5], aluno.isPresenca() ? 1 : 0); // Converte boolean para int (1 ou 0)

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
              enums.turmaEnum turmaEnum = enums.turmaEnum.valueOf(turmaString);

              aluno.setRA(cursor.getInt(0));
              aluno.setNome(cursor.getString(1));
              aluno.setTurma(turmaEnum);
              aluno.setNotaTrabalho(cursor.getInt(3));
              aluno.setNotaProva(cursor.getInt(4));
              aluno.setMedia(cursor.getDouble(5));
              aluno.setPresenca(cursor.getInt(6) == 1);
          }
      }catch(SQLException ex){
          Log.e("AlunoDao", "ERRO: AlunoDao.getById()" + ex.getMessage());
      }
      return null;
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> listaAlunos = new ArrayList<>();
        try{
            Cursor cursor = dataBase.query(tabela, colunas, null, null, null, null, null);

            if(cursor.moveToFirst()){
                do{
                    Aluno aluno = new Aluno();
                    String turmaString = cursor.getString(2);
                    enums.turmaEnum turmaEnum = enums.turmaEnum.valueOf(turmaString);

                    aluno.setRA(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setTurma(turmaEnum);
                    aluno.setNotaTrabalho(cursor.getInt(3));
                    aluno.setNotaProva(cursor.getInt(4));
                    aluno.setMedia(cursor.getDouble(5));

                    listaAlunos.add(aluno);
                }while(cursor.moveToNext());
            }
            return listaAlunos;
        }catch (SQLException ex){
            Log.e("AlunoDao", "ERRO: AlunoDao.getAll()" + ex.getMessage());
        }
        return null;
        }

}
