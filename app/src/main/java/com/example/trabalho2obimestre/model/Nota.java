package com.example.trabalho2obimestre.model;

public class Nota {
    private int id;
    private int anoLetivo;
    private String bimestre;
    private double notaAvaliacao;
    private double notaTrabalho;
    private int alunoMatricula;
    private int disciplinaId;

    public Nota(int id, int anoLetivo, String bimestre, double notaAvaliacao, double notaTrabalho, int alunoMatricula, int disciplinaId) {
        this.id = id;
        this.anoLetivo = anoLetivo;
        this.bimestre = bimestre;
        this.notaAvaliacao = notaAvaliacao;
        this.notaTrabalho = notaTrabalho;
        this.alunoMatricula = alunoMatricula;
        this.disciplinaId = disciplinaId;
    }

    public Nota() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public String getBimestre() {
        return bimestre;
    }

    public void setBimestre(String bimestre) {
        this.bimestre = bimestre;
    }

    public double getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(double notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public double getNotaTrabalho() {
        return notaTrabalho;
    }

    public void setNotaTrabalho(double notaTrabalho) {
        this.notaTrabalho = notaTrabalho;
    }

    public int getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(int alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
