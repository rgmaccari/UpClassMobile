package com.example.trabalho2obimestre.model;

public class Disciplina {

    private int id;
    private String nome;
    private int registroProfessor;

    public Disciplina(int id, String nome, int registroProfessor) {
        this.id = id;
        this.nome = nome;
        this.registroProfessor = registroProfessor;
    }

    public Disciplina(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRegistroProfessor() {
        return registroProfessor;
    }

    public void setRegistroProfessor(int registroProfessor) {
        this.registroProfessor = registroProfessor;
    }
}
