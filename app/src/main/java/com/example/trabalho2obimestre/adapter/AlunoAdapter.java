package com.example.trabalho2obimestre.adapter;
import com.example.trabalho2obimestre.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.model.ItemChamada;

import java.util.ArrayList;

import com.example.trabalho2obimestre.controller.AlunoController;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.ViewHolder> {
    private ArrayList<ItemChamada> alunos;
    private AlunoController controller;

    public AlunoAdapter(ArrayList<ItemChamada> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chamada, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemChamada item = alunos.get(position);

        // Configura as informações do aluno
        holder.tvNome.setText(item.getTvNome());
        holder.tvRa.setText(item.getAlunoRa());
        holder.checkboxPresenca.setChecked(item.isCheckboxPresenca());

        // Listener para o checkbox de presença
        holder.checkboxPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicializa o AlunoController com o contexto
                AlunoController controller = new AlunoController(holder.itemView.getContext());

                // Verifica o estado atual do CheckBox
                boolean isChecked = holder.checkboxPresenca.isChecked();

                if (isChecked) {
                    //Incrementar a presença no banco de dados se marcado
                    controller.incrementarPresenca(Integer.parseInt(item.getAlunoRa()));
                    item.setCheckboxPresenca(true);//Marcar presença no item
                } else {
                    item.setCheckboxPresenca(false);//Atualizar o item visualmente para não presente
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvRa;
        CheckBox checkboxPresenca;
        View cardExpansivel;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvRa = itemView.findViewById(R.id.alunoNumero);
            checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
            cardExpansivel = itemView.findViewById(R.id.cardExpansivel);
        }
    }

    public void updateData(ArrayList<ItemChamada> novosAlunos) {
        this.alunos.clear();
        this.alunos.addAll(novosAlunos);
        notifyDataSetChanged();
    }

}
