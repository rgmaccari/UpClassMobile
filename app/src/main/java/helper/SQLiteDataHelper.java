package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import enums.turmaEnum;
import model.Aluno;
import model.ItemChamada;

public class SQLiteDataHelper extends SQLiteOpenHelper {
    public SQLiteDataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.
                execSQL("CREATE TABLE ALUNO (RA INTEGER PRIMARY KEY, Nome TEXT,  Turma TEXT, NotaTrabalho INTEGER," +
                        " NotaProva INTEGER, Media REAL, Presenca BOOLEAN)");

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
                ItemChamada itemChamada = new ItemChamada(ra, nome, false); // Inicializando com checkbox como false
                alunos.add(itemChamada);
            }while(cursor.moveToNext());
        }cursor.close();
        return alunos;
    }

/*
* Query para inserir alunos:
INSERT INTO Aluno (RA, Nome, Turma) VALUES
(101, 'Ana', 'PRIMEIRO_ANO_A'),
(102, 'Bruno', 'PRIMEIRO_ANO_A'),
(111, 'Iara', 'PRIMEIRO_ANO_B'),
(112, 'João', 'PRIMEIRO_ANO_B'),
(201, 'Raquel', 'SEGUNDO_ANO_A'),
(202, 'Samuel', 'SEGUNDO_ANO_A'),
(211, 'Zilda', 'SEGUNDO_ANO_B'),
(212, 'Artur', 'SEGUNDO_ANO_B');*/
/*
    public void inserirDados(int ra, String nome, turmaEnum turma){
        //Cria um banco chamado db, método que permite inserção de informações na db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("RA", ra);
        values.put("Nome", nome);
        values.put("Turma", turma.toString());
        db.insert("ALUNO", null, values);
    }

    //Criação da DataBase ao iniciar o app.
    public void inserirAlunosIniciais(SQLiteDatabase db){
        inserirDados(101, "Ana", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(102, "Bruno", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(103, "Carla", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(104, "Daniel", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(105, "Elisa", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(106, "Fábio", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(107, "Gabriela", turmaEnum.PRIMEIRO_ANO_A);
        inserirDados(108, "Heitor", turmaEnum.PRIMEIRO_ANO_A);

        // 1º ano B
        inserirDados(111, "Iara", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(112, "João", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(113, "Karina", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(114, "Leonardo", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(115, "Marina", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(116, "Nathan", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(117, "Olga", turmaEnum.PRIMEIRO_ANO_B);
        inserirDados(118, "Pedro", turmaEnum.PRIMEIRO_ANO_B);

        // 2º ano A
        inserirDados(201, "Raquel", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(202, "Samuel", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(203, "Tatiana", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(204, "Ulisses", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(205, "Vanessa", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(206, "Wagner", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(207, "Xenia", turmaEnum.SEGUNDO_ANO_A);
        inserirDados(208, "Yuri", turmaEnum.SEGUNDO_ANO_A);
        // 2º ano B
        inserirDados(211, "Zilda", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(212, "Artur", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(213, "Beatriz", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(214, "Carlos", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(215, "Diana", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(216, "Eduardo", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(217, "Fernanda", turmaEnum.SEGUNDO_ANO_B);
        inserirDados(218, "Guilherme", turmaEnum.SEGUNDO_ANO_B);
    }*/
}
