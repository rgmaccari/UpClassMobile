package controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dao.AlunoDao;
import model.Aluno;

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
}
