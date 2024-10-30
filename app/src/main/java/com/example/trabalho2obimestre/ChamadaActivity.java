package com.example.trabalho2obimestre;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
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
    private Button menuSerie;
    private Button btnVoltar;
    private ChamadaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar o controller p obter a lista de alunos
        controller = new AlunoController(this);
        ArrayList<Aluno> chamada = controller.retornarTodosAlunos();
        adapter = new ChamadaAdapter(chamada, this);
        recyclerView.setAdapter(adapter);
        adapter.setListaAlunosOriginal(chamada);

        // Configurar botão de seleção de série e filtro
        menuSerie = findViewById(R.id.menuSerie);
        menuSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(ChamadaActivity.this, menuSerie);
                popupMenu.inflate(R.menu.serie);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String serie = menuItem.getTitle().toString();
                        // Filtra ao selecionar a série
                        Log.d("ChamadaActivity", "Série selecionada: " + serie);
                        adapter.filtrarPorSerie(serie);
                        adapter.notifyDataSetChanged(); // Notifica o adapter sobre a mudança
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // Define a lista completa de alunos para uso no filtro e inicializa com a primeira turma
        Log.d("ChamadaActivity", "Alunos carregados do controller: " + chamada.size());
        adapter.setListaAlunosOriginal(chamada);
        if (chamada != null && !chamada.isEmpty()) {
            String primeiraSerie = chamada.get(0).getTurma().name();
            Log.d("ChamadaActivity", "Filtrando por série: " + primeiraSerie);
            adapter.filtrarPorSerie(primeiraSerie);
        }
    }
}