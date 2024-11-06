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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.controller.ChamadaController;
import com.example.trabalho2obimestre.model.Disciplina;
import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.trabalho2obimestre.adapter.ItemChamadaAdapter;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Turma;
import com.example.trabalho2obimestre.utils.DatePickerFragment;

public class ChamadaActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener{

    private RecyclerView recyclerView;
    private ItemChamadaAdapter adapter;

    private ChamadaController controller;
    private CardView cardView;
    private Button btnTurma;
    private Button btnVoltar;
    private Button btnData;
    private Button btnSalvar;
    private Button btnDisciplina;

    private PopupMenu popupMenu;
    private MenuInflater inflater;

    private int registroProf;

    private int itemDisciplinaId;
    private int itemTurmaId;
    private Date dataChamada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);


        controller = new ChamadaController(this);

        //Como ainda não foi implementado método de login, por padrão utilizamos o professor id 1
        registroProf = 1;


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
                ArrayList<Disciplina> itens = controller.listDisciplinasByProf(registroProf);
                showDisciplinaPopupMenu(view, R.menu.disciplina, itens);
            }
        });

        //Botão Menu Turma e seu comportamento ao ser clicado:
        btnTurma = findViewById(R.id.btnTurma);
        btnTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               ArrayList<Turma> itens = controller.listTurmasPorDisciplina(itemDisciplinaId);
               showTurmaPopupMenu(view, R.menu.turma, itens);
            }
        });

        //Botão Salvar
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setVisibility(View.GONE);//Garantir a visibilidade (não visivel)
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPresenca();
            }
        });

    }


    //Estrutura de seleção do menu PopUp:
    private void showTurmaPopupMenu(View view, int menuId, ArrayList<Turma> itens){

        popupMenu = new PopupMenu(this, view);

        for (Turma item : itens){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNomeTurma());
        }

        inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemTurmaId = item.getItemId();
                if(itemTurmaId != 0) {
                    btnTurma.setText(item.getTitle());
                    return true;
                }
                return false;

            }
        });
        popupMenu.show();
    }

    private void showDisciplinaPopupMenu(View view, int menuId, ArrayList<Disciplina> itens){

        popupMenu = new PopupMenu(this, view);

        for (Disciplina item : itens){
            popupMenu.getMenu().add(0, item.getId(), 0, item.getNome());
        }

        inflater = popupMenu.getMenuInflater();
        inflater.inflate(menuId, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                itemDisciplinaId = item.getItemId();

                if(itemDisciplinaId != 0) {
                    btnDisciplina.setText(item.getTitle());

                    return true;
                }

                return false;

            }
        });
        popupMenu.show();
    }

    private void exibirAlunosPorTurma(){
        ArrayList<Aluno> alunos = controller.retornarAlunosPorTurma(itemTurmaId);
        atualizaLista(alunos);
    }

    //Atualiza a lista de alunos.
    private void atualizaLista(ArrayList<Aluno> alunos){
        //Adapter com a lista sendo inseridos no RecycleView
        ArrayList<ItemChamada> itemChamadas = controller.converterAlunosParaItemChamada(alunos, itemDisciplinaId, dataChamada);
        adapter = new ItemChamadaAdapter(itemChamadas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

//    //Método que atualiza o RecyclerView com a nova lista:
//    // ChamadaActivity.java
//    private void updateRecyclerView(ArrayList<Aluno> alunos) {
//        ArrayList<ItemChamada> listaAlunos = new ArrayList<>();
//        for (Aluno aluno : alunos) {
//            ItemChamada item = new ItemChamada(String.valueOf(aluno.getMatricula()), aluno.getNome(), false);
//            listaAlunos.add(item);
//        }
//    }

    //Método para passar os parâmetros de data:
    @Override
    public void onDateSelected(int year, int month, int day) {
        // Ação ao selecionar uma data
        String dataSelecionada = day + "/" + (month + 1) + "/" + year;
        //Toast.makeText(ChamadaActivity.this, "Data selecionada: " + dataSelecionada, Toast.LENGTH_SHORT).show();
        btnData.setText(dataSelecionada); // Exibir a data no botão ou TextView

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        dataChamada = calendar.getTime();


        cardView = findViewById(R.id.cardView);
        cardView.setVisibility(View.VISIBLE);

        exibirAlunosPorTurma();
        btnSalvar.setVisibility(View.VISIBLE);
        //
    }


    public void salvarPresenca() {
        ArrayList<ItemChamada> listaItemChamada = ((ItemChamadaAdapter) recyclerView.getAdapter())
                .getListaItemChamadaAtualizada();

        controller.salvarChamada(listaItemChamada, dataChamada, itemDisciplinaId);

        Toast.makeText(this, "Presença salva com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

}
