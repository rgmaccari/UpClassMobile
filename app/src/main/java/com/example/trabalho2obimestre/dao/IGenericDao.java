package com.example.trabalho2obimestre.dao;

import java.util.ArrayList;


public interface IGenericDao<T> {
    long insert(T obj);
    long update(T obj);
    long delete (T obj);
    T getById(long id);
    ArrayList<T> getAll();
}
