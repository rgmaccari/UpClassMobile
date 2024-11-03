package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.TurmaDao;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class TurmaController {

    private Context context;

    public TurmaController(Context context) {
        this.context = context;
    }

    public ArrayList<Turma> listTurmasPorDisciplina(int disciplinaId){
        return TurmaDao.getInstancia(context).buscarTurmasPorDisciplina(disciplinaId);
    }
}
