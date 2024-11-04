package com.example.trabalho2obimestre.model;

public class ItemMedias {
    private String nome;
    private double media;
    private double notaTrabalho1;
    private double notaTrabalho2;
    private double notaTrabalho3;
    private double notaTrabalho4;
    private double notaProva1;
    private double notaProva2;
    private double notaProva3;
    private double notaProva4;

    public ItemMedias(String nome, double media, double notaTrabalho1, double notaTrabalho2, double notaTrabalho3, double notaTrabalho4, double notaProva1, double notaProva2, double notaProva3, double notaProva4) {
        this.nome = nome;
        this.media = media;
        this.notaTrabalho1 = notaTrabalho1;
        this.notaTrabalho2 = notaTrabalho2;
        this.notaTrabalho3 = notaTrabalho3;
        this.notaTrabalho4 = notaTrabalho4;
        this.notaProva1 = notaProva1;
        this.notaProva2 = notaProva2;
        this.notaProva3 = notaProva3;
        this.notaProva4 = notaProva4;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getNotaTrabalho1() {
        return notaTrabalho1;
    }

    public void setNotaTrabalho1(double notaTrabalho1) {
        this.notaTrabalho1 = notaTrabalho1;
    }

    public double getNotaTrabalho2() {
        return notaTrabalho2;
    }

    public void setNotaTrabalho2(double notaTrabalho2) {
        this.notaTrabalho2 = notaTrabalho2;
    }

    public double getNotaTrabalho3() {
        return notaTrabalho3;
    }

    public void setNotaTrabalho3(double notaTrabalho3) {
        this.notaTrabalho3 = notaTrabalho3;
    }

    public double getNotaTrabalho4() {
        return notaTrabalho4;
    }

    public void setNotaTrabalho4(double notaTrabalho4) {
        this.notaTrabalho4 = notaTrabalho4;
    }

    public double getNotaProva1() {
        return notaProva1;
    }

    public void setNotaProva1(double notaProva1) {
        this.notaProva1 = notaProva1;
    }

    public double getNotaProva3() {
        return notaProva3;
    }

    public void setNotaProva3(double notaProva3) {
        this.notaProva3 = notaProva3;
    }

    public double getNotaProva2() {
        return notaProva2;
    }

    public void setNotaProva2(double notaProva2) {
        this.notaProva2 = notaProva2;
    }

    public double getNotaProva4() {
        return notaProva4;
    }

    public void setNotaProva4(double notaProva4) {
        this.notaProva4 = notaProva4;
    }
}
