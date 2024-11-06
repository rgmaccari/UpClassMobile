package com.example.trabalho2obimestre.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Aluno;
import java.util.ArrayList;

public class NotaDao {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase dataBase;
    private String[] colunas = {"matricula", "nome", "cpf"};
    private String tabela = "ALUNO";
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

    public ArrayList<Aluno> buscarAlunosPorDisciplinaETurma(int disciplinaId, int turmaId) {
        ArrayList<Aluno> alunos = new ArrayList<>();
        Cursor cursor = null; // Declarar o cursor fora do bloco try

        try {
            String query = "SELECT a.id, a.nome, a.cpf FROM Alunos a " +
                    "JOIN Matricula m ON a.id = m.aluno_id " +
                    "WHERE m.disciplina_id = ? AND m.turma_id = ?";
            cursor = dataBase.rawQuery(query, new String[]{String.valueOf(disciplinaId), String.valueOf(turmaId)});

            if(cursor.moveToFirst()) {
                do{
                    Aluno aluno = new Aluno();
                    aluno.setMatricula(cursor.getInt(0));
                    aluno.setNome(cursor.getString(1));
                    aluno.setCpf(cursor.getString(2));
                    alunos.add(aluno);
                }while(cursor.moveToNext());
            }
        }catch(Exception e) {
            e.printStackTrace(); // Loga o erro no console
        }finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return alunos;
    }


}
