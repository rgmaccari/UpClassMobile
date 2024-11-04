package com.example.trabalho2obimestre.model;

import java.util.ArrayList;

public class Aluno extends Pessoa{

    private int matricula;
    private ArrayList<Presenca> presencas;
    private ArrayList<Notas> notas;
    private int turma;

    public Aluno() {
    }

    public Aluno(String nome, String cpf, int matricula, ArrayList<Presenca> presencas, ArrayList<Notas> notas, int turma) {
        super(nome, cpf);
        this.matricula = matricula;
        this.presencas = presencas;
        this.notas = notas;
        this.turma = turma;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public ArrayList<Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(ArrayList<Presenca> presencas) {
        this.presencas = presencas;
    }

    public ArrayList<Notas> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Notas> notas) {
        this.notas = notas;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    //    private int RA;
//    private String nome;
//    private int notaTrabalho;
//    private int notaProva;
//    private double media;
//    private int presenca = 0;
//    private String turma;
//
//    public Aluno() {
//    }
//
//    public Aluno(int RA, String nome, int notaTrabalho, int notaProva, double media, int presenca, String turma) {
//        this.RA = RA;
//        this.nome = nome;
//        this.notaTrabalho = notaTrabalho;
//        this.notaProva = notaProva;
//        this.media = media;
//        this.presenca = presenca;
//        this.turma = turma;
//    }
//
//    public int getRA() {
//        return RA;
//    }
//
//    public void setRA(int RA) {
//        this.RA = RA;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public int getNotaTrabalho() {
//        return notaTrabalho;
//    }
//
//    public void setNotaTrabalho(int notaTrabalho) {
//        this.notaTrabalho = notaTrabalho;
//    }
//
//    public int getNotaProva() {
//        return notaProva;
//    }
//
//    public void setNotaProva(int notaProva) {
//        this.notaProva = notaProva;
//    }
//
//    public double getMedia() {
//        return media;
//    }
//
//    public void setMedia(double media) {
//        this.media = media;
//    }
//
//    public int getPresenca() {
//        return presenca;
//    }
//
//    public void setPresenca(int presenca) {
//        this.presenca = presenca;
//    }
//
//    public String getTurma() {
//        return turma;
//    }
//
//    public void setTurma(String turma) {
//        this.turma = turma;
//    }
}
