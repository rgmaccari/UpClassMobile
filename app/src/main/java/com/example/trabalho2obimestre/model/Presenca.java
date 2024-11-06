package com.example.trabalho2obimestre.model;

import java.util.Date;

public class Presenca {

    private int id;
    private Date data;
    private boolean presente;
    private int disciplinaId;
    public int alunoMatricula;

    public Presenca(int id, Date data, boolean presente, int disciplinaId, int alunoMatricula) {
        this.id = id;
        this.data = data;
        this.presente = presente;
        this.disciplinaId = disciplinaId;
        this.alunoMatricula = alunoMatricula;
    }

    public Presenca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public int getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(int alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }
}
