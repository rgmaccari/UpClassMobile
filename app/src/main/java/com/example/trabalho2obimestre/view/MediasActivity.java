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
import com.example.trabalho2obimestre.adapter.MediasAdapter;
import com.example.trabalho2obimestre.controller.AlunoController;
import com.example.trabalho2obimestre.controller.TurmaController;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.ItemChamada;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class MediasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAlunos;
    private MediasAdapter adapter;
    private AlunoController controller;
    private TurmaController turmaController;
    private Button btnVoltar;
    private Button menuTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medias);

        turmaController = new TurmaController(this);

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        controller = new AlunoController(this);

        ArrayList<Aluno> alunos = controller.retornarTodosAlunos();

        adapter = new MediasAdapter(alunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlunos.setAdapter(adapter);

        //Btn voltar.
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        menuTurma = findViewById(R.id.menuTurma);
        menuTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Turma> turmas = turmaController.listTurmasPorDisciplina();
            }
    }



    //Seleção de turma.
    //Seleção de ano letivo.
    //CardView expansível para abrir as médias e notas do aluno.
}