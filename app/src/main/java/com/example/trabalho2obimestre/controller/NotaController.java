package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.NotaDao;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.Notas;
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

    public ArrayList<Turma> listTurmasPorDisciplina(int itemDisciplinaId) {
        return turmaController.listTurmasPorDisciplina(itemDisciplinaId);
    }

    public ArrayList<Aluno> retornarAlunosPorTurma(int itemTurmaId) {
        return alunoController.retornarAlunosPorTurma(itemTurmaId);
    }


}
