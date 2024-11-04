package com.example.trabalho2obimestre.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.adapter.AlunoAdapter;
import com.example.trabalho2obimestre.controller.AlunoController;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;

public class MediasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAlunos;
    private AlunoAdapter adapter;
    private AlunoController controller;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medias);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        controller = new AlunoController(this);

        ArrayList<Aluno> alunos = controller.retornarTodosAlunos();
        ArrayList<ItemChamada> itemChamadas = controller.converterAlunosParaItemChamada(alunos);

        adapter = new AlunoAdapter(itemChamadas);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlunos.setAdapter(adapter);


    }

    //Seleção de turma.
    //Seleção de ano letivo.
    //CardView expansível para abrir as médias e notas do aluno.
}