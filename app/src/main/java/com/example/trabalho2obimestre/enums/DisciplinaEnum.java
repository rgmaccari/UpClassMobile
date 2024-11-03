package com.example.trabalho2obimestre.enums;

public enum DisciplinaEnum {
    MATEMATICA("Matemática"),
    PORTUGUES("Português"),
    QUIMICA("Química"),
    FISICA("Física"),
    BIOLOGIA("Biologia"),
    GEOGRAFIA("Geografia"),
    HISTORIA("História"),
    SOCIOLOGIA("Sociologia"),
    FILOSOFIA("Filosofia"),
    INGLES("Inglês"),
    ARTE("Arte"),
    ED_FISICA("Ed_Física");

    private final String descricao;

    DisciplinaEnum(String descricao) {
        this.descricao = descricao;
    }
}
