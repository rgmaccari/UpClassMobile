package com.example.trabalho2obimestre;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.ChamadaAdapter;
import controller.AlunoController;
import model.Aluno;
import model.ItemChamada;

public class ChamadaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlunoController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);

        controller = new AlunoController(this);
        RecyclerView recyclerView = findViewById(R.id.recycleView);

        ArrayList<Aluno> chamada = controller.retornarTodosAlunos();

        ArrayList<Aluno> listaDeChamada = new ArrayList<>();
        //listaDeChamada.add(new Aluno("RA123", "Nome 1", false));
        //listaDeChamada.add(new Aluno("RA456", "Nome 2", true));



        ChamadaAdapter adapter = new ChamadaAdapter(chamada, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}