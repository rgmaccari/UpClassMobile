package com.example.trabalho2obimestre.controller;

import android.content.Context;

import com.example.trabalho2obimestre.dao.AlunoDao;
import com.example.trabalho2obimestre.helper.SQLiteDataHelper;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.ItemChamada;
import com.example.trabalho2obimestre.model.Notas;

import java.util.ArrayList;

public class AlunoController {

    private Context context;

    public AlunoController(Context context) {
        this.context = context;
    }

    public String salvarAluno(){
        return null;
    }

    public ArrayList<Aluno> retornarTodosAlunos(){
        return AlunoDao.getInstancia(context).getAll();
    }

    public Aluno retornarAlunoPorId(long id){
        return AlunoDao.getInstancia(context).getById(id);
    }

    public ArrayList<Aluno> retornarAlunosPorTurma(int turmaId) {
        return AlunoDao.getInstancia(context).buscarAlunosPorTurma(turmaId);
    }

    //Para listar os alunos por turma, o objeto Aluno tem que ser convertido em ItemChamda,
    //No RecycleView irá ser exibido ItemChamada apenas com as informações necessárias na tela.
    public ArrayList<ItemChamada> converterAlunosParaItemChamada(ArrayList<Aluno> alunos) {
        ArrayList<ItemChamada> itemChamadas = new ArrayList<>();
        for (Aluno aluno : alunos) {
            ItemChamada item = new ItemChamada(String.valueOf(aluno.getMatricula()), aluno.getNome(), false);
            itemChamadas.add(item);
        }
        return itemChamadas;
    }

    //Adicionar presença dentro do banco:
    public void incrementarPresenca(int ra){
        SQLiteDataHelper dbHelper = new SQLiteDataHelper(context, "Aluno.db", null, 1);
        dbHelper.incrementarPresenca(ra);
    }

    //Retornar os alunos com nota por turma
    public ArrayList<Aluno> retornarAlunosComNotasPorTurmaEAno(int turmaId) {
        ArrayList<Aluno> alunos = AlunoDao.getInstancia(context).buscarAlunosPorTurma(turmaId);

        NotaController notaController = new NotaController(context); // Criar uma instância do NotaController


        for (Aluno aluno : alunos) {
            ArrayList<Notas> notas = notaController.listarNotasPorAluno(aluno.getMatricula());
            aluno.setNotas(notas);
        }

        return alunos;
    }


}
