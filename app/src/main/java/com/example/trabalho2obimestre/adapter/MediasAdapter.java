package com.example.trabalho2obimestre.adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.controller.NotaController;
import com.example.trabalho2obimestre.model.Aluno;
import com.example.trabalho2obimestre.model.Nota;

import java.util.ArrayList;

public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.MediasViewHolder> {

    private ArrayList<Aluno> listaAlunos;
    private NotaController notaController;
    private int itemDisciplinaId;
    private int itemAnoLetivo;

    public MediasAdapter(ArrayList<Aluno> listaAlunos, NotaController notaController, int itemDisciplinaId, int itemAnoLetivo) {
        this.listaAlunos = listaAlunos;
        this.notaController = notaController;
        this.itemDisciplinaId = itemDisciplinaId;
        this.itemAnoLetivo = itemAnoLetivo;
    }

    @NonNull
    @Override
    public MediasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medias, parent, false);
        return new MediasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediasViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        Log.d("MediasAdapter", "Exibindo notas para o aluno: " + aluno.getMatricula());

        holder.tvNome.setText(aluno.getNome());
        holder.tvMatricula.setText(String.valueOf(aluno.getMatricula()));

        ArrayList<Nota> listaNotas = notaController.retornarNotasPorAluno(aluno.getMatricula(), itemDisciplinaId, itemAnoLetivo);

        for (Nota nota : listaNotas) {
            String bimestre = nota.getBimestre();

            switch (bimestre) {
                case "1":
                    holder.notaTrabalho1.setText(String.valueOf(nota.getNotaTrabalho()));
                    holder.notaProva1.setText(String.valueOf(nota.getNotaAvaliacao()));
                    break;
                case "2":
                    holder.notaTrabalho2.setText(String.valueOf(nota.getNotaTrabalho()));
                    holder.notaProva2.setText(String.valueOf(nota.getNotaAvaliacao()));
                    break;
                case "3":
                    holder.notaTrabalho3.setText(String.valueOf(nota.getNotaTrabalho()));
                    holder.notaProva3.setText(String.valueOf(nota.getNotaAvaliacao()));
                    break;
                case "4":
                    holder.notaTrabalho4.setText(String.valueOf(nota.getNotaTrabalho()));
                    holder.notaProva4.setText(String.valueOf(nota.getNotaAvaliacao()));
                    break;
            }
        }

        View.OnClickListener toggleListener = v -> toggleCardVisibility(holder.cardNotas, holder.imgArrow);
        holder.tvNome.setOnClickListener(toggleListener);
        holder.tvMatricula.setOnClickListener(toggleListener);
        holder.imgArrow.setOnClickListener(toggleListener);
    }

    //Visibilidade do cardView com as informações e seta indicadora (arrow).
    private void toggleCardVisibility(CardView cardNotas, ImageView imgArrow) {
        if (cardNotas.getVisibility() == View.GONE) {
            cardNotas.setVisibility(View.VISIBLE);
            imgArrow.setImageResource(R.drawable.arrowdown);
        } else {
            cardNotas.setVisibility(View.GONE);
            imgArrow.setImageResource(R.drawable.arrowright);
        }
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    static class MediasViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvMatricula;
        TextView notaTrabalho1, notaProva1;
        TextView notaTrabalho2, notaProva2;
        TextView notaTrabalho3, notaProva3;
        TextView notaTrabalho4, notaProva4;
        CardView cardNotas;
        ImageView imgArrow;

        public MediasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvMatricula = itemView.findViewById(R.id.tvMatricula);
            cardNotas = itemView.findViewById(R.id.cardNotas);
            imgArrow = itemView.findViewById(R.id.imgArrow);

            notaTrabalho1 = itemView.findViewById(R.id.notaTrabalho1);
            notaProva1 = itemView.findViewById(R.id.notaProva1);
            notaTrabalho2 = itemView.findViewById(R.id.notaTrabalho2);
            notaProva2 = itemView.findViewById(R.id.notaProva2);
            notaTrabalho3 = itemView.findViewById(R.id.notaTrabalho3);
            notaProva3 = itemView.findViewById(R.id.notaProva3);
            notaTrabalho4 = itemView.findViewById(R.id.notaTrabalho4);
            notaProva4 = itemView.findViewById(R.id.notaProva4);
        }
    }


}
