package controller;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDao;
import model.Aluno;
import model.ItemChamada;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(){
        return null;
    }

    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstancia(context).getAll();
    }

    public Aluno retornarAlunoPorId(long id){
        return AlunoDao.getInstancia(context).getById(id);
    }

    public ArrayList<Aluno> retornarAlunosPorTurma(String turma) {
        return AlunoDao.getInstancia(context).buscarAlunosPorTurma(turma);
    }

}
