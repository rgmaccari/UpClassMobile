package com.example.trabalho2obimestre;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

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


        //Determina a View para exibir o menu de contexto.
        View menuSerie = findViewById(R.id.menuSerie);
        registerForContextMenu(menuSerie);


        controller = new AlunoController(this);
        RecyclerView recyclerView = findViewById(R.id.recycleView);

        ArrayList<Aluno> chamada = controller.retornarTodosAlunos();
        ArrayList<Aluno> listaDeChamada = new ArrayList<>();

        ChamadaAdapter adapter = new ChamadaAdapter(chamada, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    //Cria o menu de contexto efetivamente.
    @Override
    public void onCreateContextMenu(ContextMenu menuSerie, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menuSerie, v, menuInfo);
        getMenuInflater().inflate(R.menu.serie, menuSerie);
    }

    //Atribui o comportamento do menu de contexto.
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.btnPrimeiroAnoA) {
            controller.retornarTodosAlunos();
            return true;
        } else if (itemId == R.id.btnPrimeiroAnoB) {
            controller.retornarTodosAlunos();
            return true;
        } else if (itemId == R.id.btnSegundoAnoA) {
            controller.retornarTodosAlunos();
            return true;
        } else if (itemId == R.id.btnSegundoAnoB) {
            controller.retornarTodosAlunos();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    public void onClickVoltar(View view) {
        finish();
    }



}