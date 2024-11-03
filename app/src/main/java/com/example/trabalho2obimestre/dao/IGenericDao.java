package com.example.trabalho2obimestre.dao;

import java.util.ArrayList;
import java.util.List;

public interface IGenericDao<Aluno> {
    long insert(Aluno aluno);
    long update(Aluno aluno);
    long delete (Aluno aluno);
    Aluno getById(long id);
    ArrayList<Aluno> getAll();
}
