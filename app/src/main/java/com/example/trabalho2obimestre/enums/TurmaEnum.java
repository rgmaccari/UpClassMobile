package com.example.trabalho2obimestre.enums;

public enum TurmaEnum {
    PRIMEIRO_ANO_A("1A"),
    PRIMEIRO_ANO_B("1B"),
    SEGUNDO_ANO_A("2A"),
    SEGUNDO_ANO_B("2B"),
    TERCEIRO_ANO_A("3A"),
    TERCEIRO_ANO_B("3B");

    public final String descricao;

    private TurmaEnum(String descricao) {

        this.descricao = descricao;
    }
}
