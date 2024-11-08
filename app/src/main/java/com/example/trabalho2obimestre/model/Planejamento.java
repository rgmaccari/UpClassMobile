package com.example.trabalho2obimestre.model;

public class Planejamento {

    private int id;
    private String descricao;
    private boolean completo;
    private int disciplinaId;
    private int turmaId;

    public Planejamento() {
    }

    public Planejamento(int id, String descricao, boolean stFeito, int disciplinaId, int turmaId) {
        this.id = id;
        this.descricao = descricao;
        this.completo = stFeito;
        this.disciplinaId = disciplinaId;
        this.turmaId = turmaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }
}
