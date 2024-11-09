package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.DisciplinaDao;
import com.example.trabalho2obimestre.model.Disciplina;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DisciplinaController {

    private Context context;

    public DisciplinaController(Context context) {
        this.context = context;
    }

    public ArrayList<Disciplina> listDisciplinasByProf(int regProf){
        return DisciplinaDao.getInstancia(context).buscarDisciplinasPorProfessor(regProf);
    }
}
