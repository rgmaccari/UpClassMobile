package com.example.trabalho2obimestre.view;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.adapter.PlanejamentoAdapter;
import com.example.trabalho2obimestre.controller.PlanejamentoController;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.Planejamento;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class PlanejamentoActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnDisciplina;
    private Button btnTurma;

    private PopupMenu popupMenu;
    private MenuInflater inflater;
    private PlanejamentoAdapter adapter;

    private PlanejamentoController controller;
    private int registroProf;
    private int itemDisciplinaId;
    private int itemTurmaId;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planejamento);

        controller = new PlanejamentoController(this);
        registroProf = 1;

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlanejamentoAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDisciplina = findViewById(R.id.btnDisciplina);
        btnDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Disciplina> disciplinas = controller.listDisciplinasByProf(registroProf);
                showDisciplinaPopupMenu(view, R.menu.disciplina, disciplinas);
            }
        });

        btnTurma = findViewById(R.id.btnTurma);
        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemDisciplinaId <= 0) {
                    Toast.makeText(PlanejamentoActivity.this, "Selecione uma disciplina primeiro.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Turma> turmas = controller.listTurmasPorDisciplina(itemDisciplinaId);
                showTurmaPopupMenu(view, R.menu.turma, turmas);
            }
        });
    }

    private void showDisciplinaPopupMenu(View view, int menuId, ArrayList<Disciplina> disciplinas) {
        popupMenu = new PopupMenu(this, view);
        for (Disciplina item : disciplinas) {
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNome());
        }

        inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemDisciplinaId = item.getItemId();
                if (itemDisciplinaId != 0) {
                    btnDisciplina.setText(item.getTitle());
                    exibirPlanejamentos();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void showTurmaPopupMenu(View view, int menuId, ArrayList<Turma> turmas) {
        popupMenu = new PopupMenu(this, view);
        for (Turma item : turmas) {
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNomeTurma());
        }

        inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemTurmaId = item.getItemId();
                if (itemTurmaId != 0) {
                    btnTurma.setText(item.getTitle());
                    exibirPlanejamentos();
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void exibirPlanejamentos() {
        if (itemDisciplinaId > 0 && itemTurmaId > 0) {
            ArrayList<Planejamento> planejamentos = controller.listPlanejamentosPorTumaEDisciplina(itemDisciplinaId, itemTurmaId);
            // Atualize a lista de planejamentos no RecyclerView
            PlanejamentoAdapter adapter = new PlanejamentoAdapter(planejamentos);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Selecione uma disciplina e uma turma", Toast.LENGTH_SHORT).show();
        }
    }
}
