package com.example.trabalho2obimestre.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;

public class SQLiteDataHelper extends SQLiteOpenHelper {
    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.
                execSQL("CREATE TABLE ALUNO (RA INTEGER PRIMARY KEY, Nome TEXT,  Turma TEXT, NotaTrabalho INTEGER," +
                        " NotaProva INTEGER, Media REAL, Presenca INTEGER DEFAULT 0)");

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
