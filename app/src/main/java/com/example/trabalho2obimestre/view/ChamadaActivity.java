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
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.controller.DisciplinaController;
import com.example.trabalho2obimestre.controller.TurmaController;
import com.example.trabalho2obimestre.enums.TurmaEnum;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;
import java.util.Date;

import com.example.trabalho2obimestre.adapter.ChamadaAdapter;
import com.example.trabalho2obimestre.controller.AlunoController;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Professor;
import com.example.trabalho2obimestre.model.Turma;
import com.example.trabalho2obimestre.utils.DatePickerFragment;

public class ChamadaActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener{

    private RecyclerView recyclerView;
    private AlunoController alunoController;
    private TurmaController turmaController;
    private DisciplinaController disciplinaController;
    private int registroProf;
    private Button btnTurma;
    private Button btnVoltar;
    private Button btnData;
    private Button btnSalvar;
    private Button btnDisciplina;

    private int itemDisciplinaId;
    private int itemTurmaId;
    private Date dataChamada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);

        //Como ainda n foi implementado método de login, por padrão utilizamos o professor id 1
        registroProf = 1;

        alunoController = new AlunoController(this);
        turmaController = new TurmaController(this);
        disciplinaController = new DisciplinaController(this);

        recyclerView = findViewById(R.id.recyclerView);

        //Botão Voltar
        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Exibir o menu de data:
        btnData = findViewById(R.id.btnData);
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(itemDisciplinaId <= 0 || itemTurmaId <= 0) return;

                DialogFragment dataPicker = new DatePickerFragment(ChamadaActivity.this); // Corrige para passar "this"
                dataPicker.show(getSupportFragmentManager(), "DatePicker");
            }
        });

        //Menu Disciplina
        btnDisciplina = findViewById(R.id.btnDisciplina);
        btnDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Disciplina> itens = disciplinaController.listDisciplinasByProf(registroProf);
                showDisciplinaPopupMenu(view, R.menu.disciplina, itens);
            }
        });

        //Botão Menu Turma e seu comportamento ao ser clicado:
        btnTurma = findViewById(R.id.btnTurma);
        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               ArrayList<Turma> itens = turmaController.listTurmasPorDisciplina(itemDisciplinaId);
               showTurmaPopupMenu(view, R.menu.turma, itens);
            }
        });

        //Botão Salvar
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setVisibility(View.GONE);//Garantir a visibilidade (não visivel)

    }


    //Estrutura de seleção do menu PopUp:
    private void showTurmaPopupMenu(View view, int menuId, ArrayList<Turma> itens){

        PopupMenu popupMenu = new PopupMenu(this, view);

        for (Turma item : itens){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNomeTurma());
        }

        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemTurmaId = item.getItemId();
                if(itemTurmaId != 0) {
                    Toast.makeText(ChamadaActivity.this, "Turma selecionada: " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;

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
                    Toast.makeText(ChamadaActivity.this, "Disciplina selecionada: " + item.getTitle().toString(), Toast.LENGTH_SHORT).show();

                    return true;
                }

                return false;

            }
        });
        popupMenu.show();
    }

    private void exibirAlunosPorTurma(){
        ArrayList<Aluno> alunos = alunoController.retornarAlunosPorTurma(itemTurmaId);
        atualizaLista(alunos);
    }

    //Atualiza a lista de alunos.
    private void atualizaLista(ArrayList<Aluno> alunos){
        //Adapter com a lista sendo inseridos no RecycleView
        ArrayList<ItemChamada> itemChamadas = alunoController.converterAlunosParaItemChamada(alunos);
        ChamadaAdapter adapter = new ChamadaAdapter(itemChamadas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    //Método que atualiza o RecyclerView com a nova lista:
    // ChamadaActivity.java
    private void updateRecyclerView(ArrayList<Aluno> alunos) {
        ArrayList<ItemChamada> listaAlunos = new ArrayList<>();
        for (Aluno aluno : alunos) {
            ItemChamada item = new ItemChamada(String.valueOf(aluno.getMatricula()), aluno.getNome(), false);
            listaAlunos.add(item);
        }
    }

    //Método para passar os parâmetros de data:
    @Override
    public void onDateSelected(int year, int month, int day) {
        // Ação ao selecionar uma data
        String dataSelecionada = day + "/" + (month + 1) + "/" + year;
        Toast.makeText(ChamadaActivity.this, "Data selecionada: " + dataSelecionada, Toast.LENGTH_SHORT).show();
        btnData.setText(dataSelecionada); // Exibir a data no botão ou TextView

        dataChamada = new Date(year, month, day);

        exibirAlunosPorTurma();
        btnSalvar.setVisibility(View.VISIBLE);
    }

}
