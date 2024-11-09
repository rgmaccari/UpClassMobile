package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.AlunoDao;
import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public ArrayList<Aluno> retornarAlunosPorTurma(int turmaId) {
        return AlunoDao.getInstancia(context).buscarAlunosPorTurma(turmaId);
    }

}
