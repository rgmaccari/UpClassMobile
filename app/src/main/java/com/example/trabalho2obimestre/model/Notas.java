package com.example.trabalho2obimestre.model;


import com.example.trabalho2obimestre.enums.BimestreEnum;

public class Notas {
    private int id;
    private int anoLetivo;
    private String bimestre;
    private double notaAvaliacao;
    private double notaTrabalho;

    public Notas(int id, int anoLetivo, String bimestre, double notaAvaliacao,
                 double notaTrabalho) {
        this.id = id;
        this.anoLetivo = anoLetivo;
        this.bimestre = bimestre;
        this.notaAvaliacao = notaAvaliacao;
        this.notaTrabalho = notaTrabalho;
    }

    public Notas() {
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


}
