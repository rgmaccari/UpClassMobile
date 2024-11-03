package com.example.trabalho2obimestre.helper;

import android.database.sqlite.SQLiteDatabase;

import com.example.trabalho2obimestre.enums.TurmaEnum;

public class SeedData {

    public static void insertInitialData(SQLiteDatabase db){
        db.execSQL("INSERT INTO TURMA (nomeTurma, anoLetivo) VALUES " +
                "('1A', 2024), ('1B', 2024), ('2A', 2024), ('2B', 2024), ('3A', 2024), ('3B', 2024)");

        db.execSQL("INSERT INTO ALUNO (nome, cpf, turmaId) VALUES " +
                "('João Silva', '987.654.321-00', 1)," +
                "('Maria Oliveira', '123.456.789-01', 1)," +
                "('Ana Costa', '123.456.789-03', 1)," +
                "('Pedro Santos', '987.654.321-02', 1)," +
                "('Lucas Lima', '987.654.321-04', 1)," +
                "('Beatriz Souza', '123.456.789-05', 1)," +
                "('Felipe Fernandes', '987.654.321-06', 1)," +
                "('Larissa Almeida', '123.456.789-07', 1)," +
                "('Ricardo Pereira', '987.654.321-08', 1)," +
                "('Juliana Rodrigues', '123.456.789-09', 1)," +
                "('Ana Souza', '123.456.789-09', 1)," +
                "('Larissa Rodrigues', '123.456.789-09', 1)," +
                "('João Pedro', '123.456.789-03', 1)," +
                "('Carlos Henrique', '987.654.321-20', 3)," +
                "('Fernanda Ribeiro', '123.456.789-21', 3)," +
                "('Gustavo Andrade', '987.654.321-22', 3)," +
                "('Patrícia Mendes', '123.456.789-23', 3)," +
                "('Rafael Moraes', '987.654.321-24', 3)," +
                "('Camila Torres', '123.456.789-25', 3)," +
                "('Eduardo Nascimento', '987.654.321-26', 3)," +
                "('Aline Barros', '123.456.789-27', 3)," +
                "('Marcos Ferreira', '987.654.321-28', 3)," +
                "('Vanessa Martins', '123.456.789-29', 3)");

        db.execSQL("INSERT INTO PROFESSOR (nome, cpf) VALUES ('Clara Martins', '123.456.789-00')");

        db.execSQL("INSERT INTO DISCIPLINA (nome, professorRegistro) VALUES ('Matemática', 1)");

        db.execSQL("INSERT INTO TURMA_DISCIPLINA (disciplinaId, turmaId) " +
                "VALUES (1, 1), (1, 3)");

    }
}
