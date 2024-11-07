package com.example.trabalho2obimestre.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class MediasAdapter extends RecyclerView.Adapter<MediasAdapter.MediasViewHolder> {

    private ArrayList<Aluno> listaAlunos;

    public MediasAdapter(ArrayList<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
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
        holder.tvNome.setText(aluno.getNome());
        holder.tvCpf.setText(aluno.getCpf());

        View.OnClickListener toggleListener = v -> toggleCardVisibility(holder.cardNotas);
        holder.tvNome.setOnClickListener(toggleListener);
        holder.tvCpf.setOnClickListener(toggleListener);
    }

    //Visibilidade do cardView com as informações.
    private void toggleCardVisibility(CardView cardNotas) {
        if (cardNotas.getVisibility() == View.GONE) {
            cardNotas.setVisibility(View.VISIBLE);
        } else {
            cardNotas.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    static class MediasViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvCpf;
        CardView cardNotas;

        public MediasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvCpf = itemView.findViewById(R.id.tvCpf);
            cardNotas = itemView.findViewById(R.id.cardNotas);
        }
    }

    public void updateAlunos(ArrayList<Aluno> novosAlunos) {
        this.listaAlunos.clear();
        this.listaAlunos.addAll(novosAlunos);
        notifyDataSetChanged();
    }

}
