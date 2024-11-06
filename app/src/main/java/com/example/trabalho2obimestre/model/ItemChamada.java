package com.example.trabalho2obimestre.model;

public class ItemChamada {
    private String matricula;
    private String nome;
    private boolean checkboxPresenca;

    public ItemChamada() {
    }

    public ItemChamada(String matricula, String nome, boolean checkboxPresenca) {
        this.matricula = matricula;
        this.nome = nome;
        this.checkboxPresenca = checkboxPresenca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String alunoRa) {
        this.matricula = alunoRa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isCheckboxPresenca() {
        return checkboxPresenca;
    }

    public void setCheckboxPresenca(boolean checkboxPresenca) {
        this.checkboxPresenca = checkboxPresenca;
    }
}
