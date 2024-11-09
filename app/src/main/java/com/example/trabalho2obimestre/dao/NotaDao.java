package com.example.trabalho2obimestre.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Notas;

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
        Cursor cursor = null;

        try {
            String query = "SELECT a.matricula, a.nome, a.cpf " +
                    "FROM ALUNO a " +
                    "JOIN TURMA t ON a.turmaId = t.id " +
                    "JOIN DISCIPLINA d ON t.id = d.id " +
                    "WHERE t.id = ? AND d.id = ?" +
                    "ORDER BY a.nome ASC";
            cursor = dataBase.rawQuery(query, new String[]{String.valueOf(disciplinaId), String.valueOf(turmaId)});
            Log.d("NotaDao", "Cursor count: " + cursor.getCount());

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
            Log.e("NotaDao", "Erro ao buscar alunos", e);
        }finally{
            if (cursor != null) {
                cursor.close();
            }
        }
        return alunos;
    }

    public ArrayList<Notas> buscarNotasPorAlunoBimestreDisciplina(int alunoId, int disciplinaId, int anoLetivo) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        ArrayList<Notas> listaNotas = new ArrayList<>();

        String[] columns = {"bimestre","notaTrabalho", "notaAvaliacao"};
        String selection = "alunoMatricula = ? AND disciplinaId = ? AND anoLetivo = ?";
        String[] selectionArgs = {String.valueOf(alunoId), String.valueOf(disciplinaId), String.valueOf(anoLetivo)};

        try (Cursor cursor = db.query("NOTA", columns, selection, selectionArgs, null, null, null)) {
            if (cursor.moveToFirst()) {
                Log.d("NotaDao", "Notas encontradas para o aluno: " + cursor.getCount());
                do {
                    String bimestre = cursor.getString(cursor.getColumnIndexOrThrow("bimestre"));
                    double notaTrabalho = cursor.getDouble(cursor.getColumnIndexOrThrow("notaTrabalho"));
                    double notaAvaliacao = cursor.getDouble(cursor.getColumnIndexOrThrow("notaAvaliacao"));

                    Notas notas = new Notas(alunoId, disciplinaId, bimestre, notaTrabalho, notaAvaliacao);
                    listaNotas.add(notas);
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
