package com.example.trabalho2obimestre.model;

import java.util.Date;

public class Presenca {

    private int id;
    private Date data;
    private boolean presente;

    public Presenca(int id, Date data, boolean presente) {
        this.id = id;
        this.data = data;
        this.presente = presente;
    }

    public Presenca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }


}
