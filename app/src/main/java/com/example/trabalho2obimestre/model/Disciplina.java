package com.example.trabalho2obimestre.model;

public class Disciplina {

    private int id;
    private String nome;
    private Professor professor;

    public Disciplina(int id, String nome, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
