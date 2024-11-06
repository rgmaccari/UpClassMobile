package com.example.trabalho2obimestre.controller;

import android.content.Context;
import android.util.Log;

import com.example.trabalho2obimestre.dao.PresencaDao;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.ItemChamada;
import com.example.trabalho2obimestre.model.Presenca;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;
import java.util.Date;

public class ChamadaController {

    private Context context;

    private AlunoController alunoController;
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;

    public ChamadaController(Context context) {
        this.context = context;

        alunoController = new AlunoController(context);
        turmaController = new TurmaController(context);
        disciplinaController = new DisciplinaController(context);
    }

    public ArrayList<Disciplina> listDisciplinasByProf(int registroProf) {
        return disciplinaController.listDisciplinasByProf(registroProf);
    }

    public ArrayList<Turma> listTurmasPorDisciplina(int itemDisciplinaId) {
        return turmaController.listTurmasPorDisciplina(itemDisciplinaId);
    }

    public ArrayList<Aluno> retornarAlunosPorTurma(int itemTurmaId) {
        return alunoController.retornarAlunosPorTurma(itemTurmaId);
    }

    //Para listar os alunos por turma, o objeto Aluno tem que ser convertido em ItemChamda,
    //No RecycleView irá ser exibido ItemChamada apenas com as informações necessárias na tela.
    public ArrayList<ItemChamada> converterAlunosParaItemChamada(ArrayList<Aluno> alunos, int disciplinaId, Date data) {
        ArrayList<ItemChamada> itemChamadas = new ArrayList<>();
        for (Aluno aluno : alunos) {

            Presenca presenca = PresencaDao.getInstancia(context)
                    .buscarPresenca(data, aluno.getMatricula(), disciplinaId);

            if(presenca != null){
                ItemChamada item = new ItemChamada(String.valueOf(aluno.getMatricula()), aluno.getNome(), presenca.isPresente());
                itemChamadas.add(item);
            }else {
                ItemChamada item = new ItemChamada(String.valueOf(aluno.getMatricula()), aluno.getNome(), false);
                itemChamadas.add(item);
            }

        }
        return itemChamadas;
    }

    public void salvarChamada(ArrayList<ItemChamada> listaItemChamada, Date data, int disciplinaId){

        try {

            for(ItemChamada item : listaItemChamada){

                Presenca presenca = PresencaDao.getInstancia(context)
                        .buscarPresenca(data, Integer.parseInt(item.getMatricula()), disciplinaId);

                if(presenca != null){
                    presenca.setPresente(item.isCheckboxPresenca());
                    PresencaDao.getInstancia(context).update(presenca);
                }else {
                    presenca = new Presenca();
                    presenca.setDisciplinaId(disciplinaId);
                    presenca.setAlunoMatricula(Integer.parseInt(item.getMatricula()));
                    presenca.setData(data);
                    presenca.setPresente(item.isCheckboxPresenca());

                    PresencaDao.getInstancia(context).insert(presenca);
                }

            }

        }catch (Exception ex) {
            Log.e("ChamadaController", "ChamadaController.salvarChamada(): " + ex.getMessage());
        }
    }

}
