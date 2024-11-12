package com.example.trabalho2obimestre.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Button btnAdicionar;
    private Button btnSalvar;

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
        Log.d("PlanejamentoActivity", "LayoutManager configurado no RecyclerView");

        adapter = new PlanejamentoAdapter(new ArrayList<>());
        Log.d("PlanejamentoActivity", "Adapter criado e setado no RecyclerView");
        recyclerView.setAdapter(adapter);

        exibirPlanejamentos();


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

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnAdicionar.setEnabled(false);
        btnAdicionar.setOnClickListener(view -> {
            Planejamento planejamento = adapter.addItem(itemDisciplinaId, itemTurmaId);
            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        });


        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setEnabled(false);
        btnSalvar.setOnClickListener(view -> {
            ArrayList<Planejamento> listaPlanejamentos = adapter.getListaPlanejamentos();
            Planejamento planejamento = new Planejamento();
            EditText edDescricao = findViewById(R.id.edDescricao);
            String descricao = edDescricao.getText().toString();
            planejamento.setDescricao(descricao);
            Log.d("PlanejamentoActivity", "Descrição capturada: " + descricao);
            controller.salvarPlanejamentos(listaPlanejamentos);
            Toast.makeText(PlanejamentoActivity.this, "Planejamentos salvos!", Toast.LENGTH_SHORT).show();
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
                    exibirPlanejamentos();  // Exibe os planejamentos após seleção da disciplina
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
                    exibirPlanejamentos();  // Exibe os planejamentos após seleção da turma
                    btnSalvar.setEnabled(itemDisciplinaId > 0 && itemTurmaId > 0);
                    btnAdicionar.setEnabled(itemDisciplinaId > 0 && itemTurmaId > 0);
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

            if (planejamentos != null && !planejamentos.isEmpty()) {
                adapter.updatePlanejamentosAdapter(planejamentos);
            } else {
                Toast.makeText(this, "Nenhum planejamento encontrado para essa disciplina e turma", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Selecione uma disciplina e uma turma", Toast.LENGTH_SHORT).show();
        }
    }



}
