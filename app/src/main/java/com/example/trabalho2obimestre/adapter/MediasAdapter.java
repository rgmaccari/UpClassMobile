package com.example.trabalho2obimestre.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.model.Aluno;

import java.util.ArrayList;

public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.ViewHolder> {
    private ArrayList<Aluno> alunos;
    private int cardExpansivel = -1;

    public MediasAdapter(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medias, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.tvNome.setText(aluno.getNome());

        //Configurar o estado do card inicial.
        boolean isExpanded = position == cardExpansivel;
        holder.cardExpansivel.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        if (isExpanded) {
            for (int i = 0; i < aluno.getNotas().size(); i++) {
                if (i == 0) {
                    holder.tvBimestre1.setText("1° Bimestre");
                    holder.tvNotaTrabalho1.setText(String.valueOf(aluno.getNotas().get(i).getNotaTrabalho()));
                    holder.tvNotaProva1.setText(String.valueOf(aluno.getNotas().get(i).getNotaAvaliacao()));
                } else if (i == 1) {
                    holder.tvBimestre2.setText("2° Bimestre");
                    holder.tvNotaTrabalho2.setText(String.valueOf(aluno.getNotas().get(i).getNotaTrabalho()));
                    holder.tvNotaProva2.setText(String.valueOf(aluno.getNotas().get(i).getNotaAvaliacao()));
                } else if (i == 2) {
                    holder.tvBimestre3.setText("3° Bimestre");
                    holder.tvNotaTrabalho3.setText(String.valueOf(aluno.getNotas().get(i).getNotaTrabalho()));
                    holder.tvNotaProva3.setText(String.valueOf(aluno.getNotas().get(i).getNotaAvaliacao()));
                } else if (i == 3) {
                    holder.tvBimestre4.setText("4° Bimestre");
                    holder.tvNotaTrabalho4.setText(String.valueOf(aluno.getNotas().get(i).getNotaTrabalho()));
                    holder.tvNotaProva4.setText(String.valueOf(aluno.getNotas().get(i).getNotaAvaliacao()));
                }
            }
        } else {
            //Limpa os campos.
            holder.tvBimestre1.setText("");
            holder.tvNotaTrabalho1.setText("");
            holder.tvNotaProva1.setText("");
            holder.tvBimestre2.setText("");
            holder.tvNotaTrabalho2.setText("");
            holder.tvNotaProva2.setText("");
            holder.tvBimestre3.setText("");
            holder.tvNotaTrabalho3.setText("");
            holder.tvNotaProva3.setText("");
            holder.tvBimestre4.setText("");
            holder.tvNotaTrabalho4.setText("");
            holder.tvNotaProva4.setText("");
        }

        holder.itemView.setOnClickListener(view -> {
            cardExpansivel = isExpanded ? -1 : position; //Alternar o estado de expandido
            notifyDataSetChanged(); //Atualizar a lista
        });
    }

    @Override
    public int getItemCount() {
        return alunos.size(); //Retornar o tamanho da lista de alunos;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvBimestre1, tvNotaTrabalho1, tvNotaProva1;
        TextView tvBimestre2, tvNotaTrabalho2, tvNotaProva2;
        TextView tvBimestre3, tvNotaTrabalho3, tvNotaProva3;
        TextView tvBimestre4, tvNotaTrabalho4, tvNotaProva4;
        LinearLayout cardExpansivel;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            cardExpansivel = itemView.findViewById(R.id.cardExpansivel);
            tvBimestre1 = itemView.findViewById(R.id.tvBimestre1);
            tvNotaTrabalho1 = itemView.findViewById(R.id.tvNotaTrabalho1);
            tvNotaProva1 = itemView.findViewById(R.id.tvNotaProva1);
            tvBimestre2 = itemView.findViewById(R.id.tvBimestre2);
            tvNotaTrabalho2 = itemView.findViewById(R.id.tvNotaTrabalho2);
            tvNotaProva2 = itemView.findViewById(R.id.tvNotaProva2);
            tvBimestre3 = itemView.findViewById(R.id.tvBimestre3);
            tvNotaTrabalho3 = itemView.findViewById(R.id.tvNotaTrabalho3);
            tvNotaProva3 = itemView.findViewById(R.id.tvNotaProva3);
            tvBimestre4 = itemView.findViewById(R.id.tvBimestre4);
            tvNotaTrabalho4 = itemView.findViewById(R.id.tvNotaTrabalho4);
            tvNotaProva4 = itemView.findViewById(R.id.tvNotaProva4);
        }
    }
}
