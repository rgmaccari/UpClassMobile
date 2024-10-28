package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import helper.SQLiteDataHelper;
import model.Aluno;

public class AlunoDao implements IGenericDao<Aluno>{
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase dataBase;

    private String[] colunas = {"RA", "Nome", "NotaTrabalho", "NotaProva", "Media", "Presenca", "Turma"};

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
        ContentValues values = new ContentValues();
        values.put("RA", aluno.getRA());
        values.put("Nome", aluno.getNome());
        values.put("NotaTrabalho", aluno.getNotaTrabalho());
        values.put("NotaProva", aluno.getNotaProva());
        values.put("Media", aluno.getMedia());
        values.put("Presenca", aluno.isPresenca() ? 1 : 0); // Converte boolean para int (1 ou 0)
        values.put("Turma", aluno.getTurma().name()); // Converte enum para String

        // Insere os dados na tabela e retorna o ID do novo registro
        return dataBase.insert(tabela, null, values);
    }

    @Override
    public long update(Aluno aluno) {
        return 0;
    }

    @Override
    public long delete(Aluno aluno) {
        return 0;
    }

    @Override
    public Aluno getById(long id) {
        return null;
    }

    @Override
    public List<Aluno> getAll() {
        return Collections.emptyList();
    }

}
