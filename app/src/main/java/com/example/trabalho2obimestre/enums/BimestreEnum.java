package com.example.trabalho2obimestre.enums;

public enum BimestreEnum {

    PRIMEIRO_BIMESTRE("1Bi"),
    SEGUNDO_BIMESTRE("2Bi"),
    TERCEIRO_BIMESTRE("3Bi"),
    QUARTO_BIMESTRE("4Bi");


    public final String descricao;

    BimestreEnum(String descricao) {
        this.descricao = descricao;
    }
}
