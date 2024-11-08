package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.PlanejamentoDao;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.Planejamento;
import com.example.trabalho2obimestre.model.Turma;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanejamentoController {
    private Context context;

    private TurmaController turmaController;
    private DisciplinaController disciplinaController;

    public PlanejamentoController(Context context) {
        this.context = context;
        turmaController = new TurmaController(context);
        disciplinaController = new DisciplinaController(context);
    }

    public ArrayList<Disciplina> listDisciplinasByProf(int registroProf) {
        return disciplinaController.listDisciplinasByProf(registroProf);
    }

    public ArrayList<Turma> listTurmasPorDisciplina(int itemDisciplinaId) {

        int anoVigente = LocalDate.now().getYear();
        return turmaController.listTurmasPorDisciplinaEAnoLetivo(itemDisciplinaId, anoVigente);
    }

    public ArrayList<Planejamento>
        listPlanejamentosPorTumaEDisciplina(int disciplinaId, int turmaId) {
        return PlanejamentoDao.getInstancia(context).buscaPlanejamentosPorTumaEDisciplina(disciplinaId, turmaId);
    }
}
