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
import com.example.trabalho2obimestre.adapter.AlunoAdapter;
import com.example.trabalho2obimestre.adapter.MediasAdapter;
import com.example.trabalho2obimestre.controller.AlunoController;
import com.example.trabalho2obimestre.controller.DisciplinaController;
import com.example.trabalho2obimestre.controller.TurmaController;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.ItemChamada;
import com.example.trabalho2obimestre.model.ItemMedias;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class MediasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlunos;
    private MediasAdapter adapter;
    private AlunoController controller;
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;
    private Button btnVoltar;
    private Button btnTurma;
    private Button btnAnoLetivo;
    private Button btnDisciplina;
    private int itemTurmaId;
    private int itemDisciplinaId;
    private int registroProf;//Variável para armazenar a disciplina selecionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medias);

        registroProf = 1;

        disciplinaController = new DisciplinaController(this);
        turmaController = new TurmaController(this);
        controller = new AlunoController(this);


        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        ArrayList<Aluno> alunos = controller.retornarTodosAlunos();
        adapter = new MediasAdapter(alunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlunos.setAdapter(adapter);

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDisciplina = findViewById(R.id.btnDisciplina);
        btnDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Disciplina> itens = disciplinaController.listDisciplinasByProf(registroProf);
                showDisciplinaPopupMenu(view, R.menu.disciplina, itens);
            }
        });

        btnTurma = findViewById(R.id.btnTurma);
        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Turma> itens = turmaController.listTurmasPorDisciplina(itemDisciplinaId);
                showTurmaPopupMenu(view, R.menu.turma, itens);
            }
        });

        //btn ano
        btnAnoLetivo = findViewById(R.id.btnAnoLetivo);
        btnAnoLetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnoLetivoMenu(view, R.menu.ano_letivo);
            }
        });
    }

    private void showAnoLetivoMenu(View view, int menuId){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());
        popupMenu.show();
    }

    private void showTurmaPopupMenu(View view, int turmaId, ArrayList<Turma> turmas) {

        PopupMenu popupMenu = new PopupMenu(this, view);


        for (Turma item : turmas) {
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNomeTurma());
        }

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.disciplina, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int selectedDisciplinaId = item.getItemId();
                itemDisciplinaId = selectedDisciplinaId;

                for (Turma turma : turmas) {
                    if (turma.getId() == selectedDisciplinaId) {
                        Toast.makeText(MediasActivity.this, "Disciplina selecionada: " + turma.getNomeTurma(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                return true;
            }
        });
        popupMenu.show();
    }

    private void showDisciplinaPopupMenu(View view, int menuId, ArrayList<Disciplina> itens){

        PopupMenu popupMenu = new PopupMenu(this, view);

        for (Disciplina item : itens){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNome());
        }

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemDisciplinaId = item.getItemId();

                if(itemDisciplinaId != 0) {
                    Toast.makeText(MediasActivity.this, "Disciplina selecionada: " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                    return true;
                }

                return false;

            }
        });
        popupMenu.show();
    }


}

