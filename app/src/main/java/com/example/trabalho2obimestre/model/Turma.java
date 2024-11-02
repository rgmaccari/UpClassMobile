package com.example.trabalho2obimestre.model;

import java.util.ArrayList;

public class Turma {

    private int id;
    private String nomeTurma;
    private int anoLetivo;
    private ArrayList<Disciplina> diciplinas;
    private ArrayList<Aluno> alunos;

    public Turma(int id, String nomeTurma, int anoLetivo, ArrayList<Disciplina> diciplinas, ArrayList<Aluno> alunos) {
        this.id = id;
        this.nomeTurma = nomeTurma;
        this.anoLetivo = anoLetivo;
        this.diciplinas = diciplinas;
        this.alunos = alunos;
    }

    public Turma() {
        diciplinas = new ArrayList<>();
        alunos = new ArrayList<>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public ArrayList<Disciplina> getDiciplinas() {
        return diciplinas;
    }

    public void setDiciplinas(ArrayList<Disciplina> diciplinas) {
        this.diciplinas = diciplinas;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

}
