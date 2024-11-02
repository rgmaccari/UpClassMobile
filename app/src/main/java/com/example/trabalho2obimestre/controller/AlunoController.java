package com.example.trabalho2obimestre.controller;

import android.content.Context;

import java.util.ArrayList;

import com.example.trabalho2obimestre.dao.AlunoDao;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.ItemChamada;

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

    public ArrayList<ItemChamada> converterAlunosParaItemChamada(ArrayList<Aluno> alunos) {
        ArrayList<ItemChamada> itemChamadas = new ArrayList<>();
        for (Aluno aluno : alunos) {
            ItemChamada item = new ItemChamada(String.valueOf(aluno.getRA()), aluno.getNome(), false);
            itemChamadas.add(item);
        }
        return itemChamadas;
    }

}
