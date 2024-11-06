package com.example.trabalho2obimestre.view;

import android.os.Bundle;
import android.util.Log;
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
import com.example.trabalho2obimestre.adapter.MediasAdapter;
import com.example.trabalho2obimestre.controller.AlunoController;
import com.example.trabalho2obimestre.controller.DisciplinaController;
import com.example.trabalho2obimestre.controller.NotaController;
import com.example.trabalho2obimestre.controller.TurmaController;
import com.example.trabalho2obimestre.dao.NotaDao;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.Turma;

import java.util.ArrayList;

public class MediasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MediasAdapter mediasAdapter;
    private ArrayList<Aluno> listaAlunos;
    private NotaDao notaDao;

    private int itemDisciplinaId = -1;
    private Button btnVoltar;
    private Button btnDisciplina;
    private Button btnTurma;
    private Button btnAnoLetivo;

    private NotaController notaController;
    private DisciplinaController disciplinaController;
    private int registroProf;
    private TurmaController turmaController;
    private int itemTurmaId = -1;
    //Item disciplina já está sendo declarada lá em cima.
    private String itemAnoLetivoSelecionado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medias);

        notaController = new NotaController(this);
        disciplinaController = new DisciplinaController(this);
        turmaController = new TurmaController(this);

        //Button Voltar:
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Button Disciplina:
        btnDisciplina = findViewById(R.id.btnDisciplina);
        btnDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Disciplina> disciplinas = disciplinaController.listDisciplinasByProf(registroProf);
                showDisciplinaPopupMenu(view, R.menu.disciplina, disciplinas);
            }
        });

        //Button Turma:
        btnTurma = findViewById(R.id.btnTurma);
        btnTurma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(itemDisciplinaId == -1){
                    Toast.makeText(MediasActivity.this, "Selecione uma disciplina primeiro", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<Turma> turmas = turmaController.listTurmasPorDisciplina(itemDisciplinaId);
                showTurmaPopupMenu(view, R.menu.turma, turmas);
            }
        });

        //Button Ano Letivo:
        btnAnoLetivo = findViewById(R.id.btnAnoLetivo);
        btnAnoLetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnoLetivoPopupMenu(view, R.menu.ano_letivo);
            }
        });

        recyclerView = findViewById(R.id.recyclerViewAlunos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notaDao = notaDao.getInstancia(this);
        listaAlunos = notaDao.buscarAlunosPorDisciplinaETurma(itemDisciplinaId, itemTurmaId);
        Log.d("MediasActivity", "Lista de alunos: " + listaAlunos.size());
        for (Aluno aluno : listaAlunos) {
            Log.d("MediasActivity", "Aluno: " + aluno.getNome() + ", CPF: " + aluno.getCpf());
        }

        if(listaAlunos.isEmpty()){
            Toast.makeText(this, "Nenhum aluno encontrado", Toast.LENGTH_SHORT).show();
        }else{
            mediasAdapter = new MediasAdapter(listaAlunos);
            recyclerView.setAdapter(mediasAdapter);
        }
    }

    //Menu Disciplina
    private void showDisciplinaPopupMenu(View view, int menuId, ArrayList<Disciplina> disciplinas) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        for(Disciplina item : disciplinas){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNome());
        }

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                itemDisciplinaId = item.getItemId();
                if(itemDisciplinaId != 0){
                    Toast.makeText(MediasActivity.this, "Disciplina selecionada: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    atualizarListaDeAlunos();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    //Menu Turma
    private void showTurmaPopupMenu(View view, int menuId, ArrayList<Turma> turmas){
        PopupMenu popupMenu = new PopupMenu(this, view);

        for(Turma item : turmas){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNomeTurma());
        }

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item){
                itemTurmaId = item.getItemId();
                if(itemTurmaId != 0){
                    Toast.makeText(MediasActivity.this, "Turma selecionada: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    atualizarListaDeAlunos();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    //Menu Ano:
    private void showAnoLetivoPopupMenu(View view, int menuId) {
        PopupMenu popupMenu = new PopupMenu(this, view);

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item){
                itemAnoLetivoSelecionado = item.getTitle().toString();
                Toast.makeText(MediasActivity.this, "Ano Letivo selecionado: " + itemAnoLetivoSelecionado, Toast.LENGTH_SHORT).show();
                atualizarListaDeAlunos();
                return true;
            }
        });
        popupMenu.show();
    }

    //Método para atualizar o RecycleView:
    //Utilizar a cada verificação.
    private void atualizarListaDeAlunos(){
        if(itemDisciplinaId != -1 && itemTurmaId != -1 && !itemAnoLetivoSelecionado.isEmpty()) {
            listaAlunos = notaDao.buscarAlunosPorDisciplinaETurma(itemDisciplinaId, itemTurmaId);

            if(listaAlunos.isEmpty()){
                Toast.makeText(this, "Nenhum aluno encontrado para essa seleção", Toast.LENGTH_SHORT).show();
            }else{
                mediasAdapter = new MediasAdapter(listaAlunos);
                recyclerView.setAdapter(mediasAdapter);
            }
        }else{
            Toast.makeText(this, "Selecione uma turma e ano letivo para exibir os alunos", Toast.LENGTH_SHORT).show();
        }
    }
}

