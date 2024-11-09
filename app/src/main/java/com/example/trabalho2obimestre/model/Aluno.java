package com.example.trabalho2obimestre.model;

import java.util.ArrayList;

public class Aluno extends Pessoa{

    private int matricula;
    private int turma;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, int matricula, int turma) {
        super(nome, cpf);
        this.matricula = matricula;
        this.turma = turma;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

}
