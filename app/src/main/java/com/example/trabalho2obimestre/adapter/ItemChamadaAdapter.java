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

public class ItemChamadaAdapter extends RecyclerView.Adapter<ItemChamadaAdapter.ViewHolder> {
    private ArrayList<ItemChamada> listaItemChamada;

    public ItemChamadaAdapter(ArrayList<ItemChamada> listaItemChamada) {
        this.listaItemChamada = listaItemChamada;
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
        ItemChamada item = listaItemChamada.get(position);

        // Configura as informações do aluno
        holder.tvNome.setText(item.getNome());
        holder.tvMatricula.setText(item.getMatricula());
        holder.checkboxPresenca.setChecked(item.isCheckboxPresenca());

        // Listener para o checkbox de presença
        holder.checkboxPresenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verifica o estado atual do CheckBox
                boolean isChecked = holder.checkboxPresenca.isChecked();

                if (isChecked) {
                    item.setCheckboxPresenca(true);//Marcar presença no item
                } else {
                    item.setCheckboxPresenca(false);//Atualizar o item visualmente para não presente
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaItemChamada.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvMatricula;
        CheckBox checkboxPresenca;
        View cardExpansivel;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvMatricula = itemView.findViewById(R.id.alunoNumero);
            checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
        }
    }

    public void updateData(ArrayList<ItemChamada> novosAlunos) {
        this.listaItemChamada.clear();
        this.listaItemChamada.addAll(novosAlunos);
        notifyDataSetChanged();
    }

    //Recupera a lista no seu estado atual.
    public ArrayList<ItemChamada> getListaItemChamadaAtualizada() {
        return listaItemChamada;
    }

}
