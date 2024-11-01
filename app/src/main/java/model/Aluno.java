package model;

import enums.turmaEnum;

public class Aluno {
    private int RA;
    private String nome;
    private int notaTrabalho;
    private int notaProva;
    private double media;
    private int presenca = 0;
    private String turma;

    public Aluno() {
    }

    public Aluno(int RA, String nome, int notaTrabalho, int notaProva, double media, int presenca, String turma) {
        this.RA = RA;
        this.nome = nome;
        this.notaTrabalho = notaTrabalho;
        this.notaProva = notaProva;
        this.media = media;
        this.presenca = presenca;
        this.turma = turma;
    }

    public int getRA() {
        return RA;
    }

    public void setRA(int RA) {
        this.RA = RA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNotaTrabalho() {
        return notaTrabalho;
    }

    public void setNotaTrabalho(int notaTrabalho) {
        this.notaTrabalho = notaTrabalho;
    }

    public int getNotaProva() {
        return notaProva;
    }

    public void setNotaProva(int notaProva) {
        this.notaProva = notaProva;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getPresenca() {
        return presenca;
    }

    public void setPresenca(int presenca) {
        this.presenca = presenca;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}
