package com.example.trabalho2obimestre.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context, @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.
                execSQL("CREATE TABLE TURMA (id INTEGER PRIMARY KEY AUTOINCREMENT, nomeTurma TEXT, " +
                        "anoLetivo INTEGER)");

        sqLiteDatabase.
                execSQL("CREATE TABLE ALUNO (matricula INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT,  cpf TEXT, " +
                        "turmaId INTEGER, " +
                        "FOREIGN KEY (turmaId) REFERENCES TURMA(id))");

        sqLiteDatabase.
                execSQL("CREATE TABLE PROFESSOR (registro INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT,  cpf TEXT)");

        sqLiteDatabase.
                execSQL("CREATE TABLE DISCIPLINA (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, " +
                        "professorRegistro INTEGER, " +
                        "FOREIGN KEY (professorRegistro) REFERENCES PROFESSOR(registro))");

        sqLiteDatabase.
                execSQL("CREATE TABLE TURMA_DISCIPLINA (disciplinaId INTEGER, turmaId INTEGER, " +
                        "PRIMARY KEY (disciplinaId, turmaId)," +
                        "FOREIGN KEY (disciplinaId) REFERENCES DISCIPLINA(id)," +
                        "FOREIGN KEY (turmaId) REFERENCES TURMA(id))");

        //o invés de BOOLEAN, o SQLite não possui um tipo booleano nativo. Recomenda-se usar INTEGER
        // para valores booleanos (0 para falso e 1 para verdadeiro).
        sqLiteDatabase.
                execSQL("CREATE TABLE PRESENCA (id INTEGER PRIMARY KEY, data DATE, presente INTEGER, " +
                        "   alunoMatricula INTEGER, disciplinaId INTEGER, " +
                        "FOREIGN KEY (alunoMatricula) REFERENCES ALUNO(matricula), " +
                        "FOREIGN KEY (disciplinaId) REFERENCES DISCIPLINA(id))");

        sqLiteDatabase.
                execSQL("CREATE TABLE NOTA (id INTEGER PRIMARY KEY, anoLetivo INTEGER, bimestre TEXT, " +
                        "notaTrabalho REAL, notaAvaliacao REAL, " +
                        "alunoMatricula INTEGER, disciplinaId INTEGER, " +
                        "FOREIGN KEY (alunoMatricula) REFERENCES ALUNO(matricula), " +
                        "FOREIGN KEY (disciplinaId) REFERENCES DISCIPLINA(id))");

        SeedData.insertInitialData(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


    //Método para busca de alunos por turma:
    //É usado o item chamada para puxar apenas as colunas pertinentes.
    public ArrayList<ItemChamada> buscarAlunosPorTurma(String turma){
        ArrayList<ItemChamada> alunos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ALUNO WHERE Turma = ?", new String[]{turma});

        if (cursor.moveToFirst()){
            do{
                String ra = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("RA")));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow("Nome"));
                ItemChamada itemChamada = new ItemChamada(ra, nome, false);//Inicializando com checkbox como false
                alunos.add(itemChamada);
            }while(cursor.moveToNext());
        }cursor.close();
        return alunos;
    }

    //Método para adicionar a presença dentro do banco:
    public void incrementarPresenca(int ra){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE ALUNO SET Presenca = Presenca + 1 WHERE RA = ?", new Integer[]{ra});
        db.close();
    }

}
