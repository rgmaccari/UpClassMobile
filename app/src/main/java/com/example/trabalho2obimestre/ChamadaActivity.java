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
    private ChamadaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar o adapter com uma lista vazia
        ArrayList<Aluno> listaVazia = new ArrayList<>();
        adapter = new ChamadaAdapter(listaVazia, this);
        recyclerView.setAdapter(adapter);

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
                        adapter.notifyDataSetChanged();// Filtra ao selecionar a série
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // Carregar lista completa de alunos do controller e configurar no adapter
        controller = new AlunoController(this);
        ArrayList<Aluno> chamada = controller.retornarTodosAlunos();
        Log.d("ChamadaActivity", "Alunos carregados do controller: " + chamada.size()); // Log para confirmar carregamento do controller
        adapter.setListaAlunosOriginal(chamada); // Define a lista completa de alunos para uso no filtro
    }
}