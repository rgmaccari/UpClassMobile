package com.example.trabalho2obimestre.model;

import java.util.ArrayList;

public class Turma {

    private int id;
    private String nomeTurma;
    private int anoLetivo;


    public Turma(int id, String nomeTurma, int anoLetivo) {
        this.id = id;
        this.nomeTurma = nomeTurma;
        this.anoLetivo = anoLetivo;
    }

    public Turma() {
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


}
