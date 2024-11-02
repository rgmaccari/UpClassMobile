package com.example.trabalho2obimestre.model;

public class Professor extends Pessoa{

    private int registro;

    public Professor() {
    }

    public Professor(String nome, String cpf, int registro) {
        super(nome, cpf);
        this.registro = registro;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }
}
