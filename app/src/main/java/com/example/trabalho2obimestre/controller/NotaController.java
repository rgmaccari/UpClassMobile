package com.example.trabalho2obimestre.controller;

import android.content.Context;
import android.util.Log;

import com.example.trabalho2obimestre.dao.NotaDao;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.Nota;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class NotaController{
    private Context context;

    private AlunoController alunoController;
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;

    public NotaController(Context context) {
        this.context = context;

        alunoController = new AlunoController(context);
        turmaController = new TurmaController(context);
        disciplinaController = new DisciplinaController(context);

    }

    public ArrayList<Disciplina> listDisciplinasByProf(int registroProf) {
        return disciplinaController.listDisciplinasByProf(registroProf);
    }

    public ArrayList<Turma> listTurmasPorDisciplinaEAnoLetivo(int itemDisciplinaId, int anoLetivo) {
        return turmaController.listTurmasPorDisciplinaEAnoLetivo(itemDisciplinaId, anoLetivo);
    }

    public ArrayList<Aluno> listAlunosPorTurma(int itemTurmaId){
        return alunoController.retornarAlunosPorTurma(itemTurmaId);
    }

    public ArrayList<Nota> retornarNotasPorAluno(int itemAlunoId, int disciplinaId, int anoLetivo){
        ArrayList<Nota> notas = NotaDao.getInstancia(context).buscarNotasPorAlunoBimestreDisciplinaEAnoLetivo(itemAlunoId, disciplinaId, anoLetivo);
        Log.d("NotaController", "Nota recuperadas: " + notas.size());
        return notas;
    }


}
