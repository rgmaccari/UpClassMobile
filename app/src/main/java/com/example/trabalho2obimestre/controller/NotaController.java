package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.NotaDao;
import com.example.trabalho2obimestre.model.Notas;

import java.util.ArrayList;

public class NotaController{
    private Context context;

    public NotaController(Context context) {
        this.context = context;
    }

//    public ArrayList<Notas> listarNotasPorAluno(int alunoId) {
//        return NotaDao.getInstancia(context).buscarNotasPorAluno(alunoId);
//    }

}
