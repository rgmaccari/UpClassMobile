package com.example.trabalho2obimestre;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import adapter.ChamadaAdapter;
import controller.AlunoController;
import enums.turmaEnum;
import model.Aluno;
import model.ItemChamada;
import utils.DatePickerFragment;

public class ChamadaActivity extends AppCompatActivity implements DatePickerFragment.DatePickerListener{

    private RecyclerView recyclerView;
    private AlunoController controller;
    private Button menuSerie;
    private Button btnVoltar;
    private Button selecionarData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chamada);
        controller = new AlunoController(this);
        recyclerView = findViewById(R.id.recyclerView);
        //DataPicker
        selecionarData = findViewById(R.id.selecionarData);
        selecionarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dataPicker = new DatePickerFragment(ChamadaActivity.this); // Corrige para passar "this"
                dataPicker.show(getSupportFragmentManager(), "DatePicker");
            }
        });

        //RecycleView e LayoutManager
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        //Botão Voltar
        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Botão Menu e seu comportamento ao ser clicado:
        Button menuSerie = findViewById(R.id.menuSerie);
        menuSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });
    }

    private void atualizaLista(ArrayList<Aluno> alunos){


        //Adapter com a lista sendo inseridos no RecycleView
        ArrayList<ItemChamada> itemChamadas = controller.converterAlunosParaItemChamada(alunos);
        ChamadaAdapter adapter = new ChamadaAdapter(itemChamadas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    //Estrutura de seleção do menu PopUp:
    private void showPopupMenu(View view){
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.serie, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                String turma = null;
                if (itemId == R.id.btnPrimeiroAnoA) {
                    turma = "1A";
                    exibirAlunosPorTurma(turmaEnum.PRIMEIRO_ANO_A.descricao);
                } else if (itemId == R.id.btnPrimeiroAnoB) {
                    turma = "1B";
                    exibirAlunosPorTurma(turmaEnum.PRIMEIRO_ANO_B.descricao);
                } else if (itemId == R.id.btnSegundoAnoA) {
                    turma = "2A";
                    exibirAlunosPorTurma(turmaEnum.SEGUNDO_ANO_A.descricao);
                } else if (itemId == R.id.btnSegundoAnoB) {
                    turma = "2B";
                    exibirAlunosPorTurma(turmaEnum.SEGUNDO_ANO_B.descricao);
                } else {
                    return false;
                }

                if (turma != null) {
                    controller = new AlunoController(ChamadaActivity.this);
                    ArrayList<Aluno> alunos = controller.retornarAlunosPorTurma(turma); // A chamada correta
                    updateRecyclerView(alunos);

                    return true;
                }else{
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    //Update de alunos:
    private void exibirAlunosPorTurma(String turma){
        ArrayList<Aluno> alunos = controller.retornarAlunosPorTurma(turma);
        atualizaLista(alunos);
    }




    //Método que atualiza o RecyclerView com a nova lista:
    // ChamadaActivity.java
    private void updateRecyclerView(ArrayList<Aluno> alunos) {
        ArrayList<ItemChamada> listaAlunos = new ArrayList<>();
        for (Aluno aluno : alunos) {
            ItemChamada item = new ItemChamada(String.valueOf(aluno.getRA()), aluno.getNome(), false);
            listaAlunos.add(item);
        }
    }


    //Método para passar os parâmetros de data:
    @Override
    public void onDateSelected(int year, int month, int day) {
        // Ação ao selecionar uma data
        String dataSelecionada = day + "/" + (month + 1) + "/" + year;
        Log.d("Data Selecionada", dataSelecionada);
        selecionarData.setText(dataSelecionada); // Exibir a data no botão ou TextView
    }
}

//O banco de dados não abre ao executar o App.
//Como implementar um menu para data (como a data influencia o funcionamento?)
//Alunos não estão sendo puxados para a tela de chamada ao selecionar a turma.
//Como fazer com que o Boolean da checkbox atribua 1 à presença do aluno dentro do banco.
//Como reorganizar os codigos para utilização em mais de um contexto.

