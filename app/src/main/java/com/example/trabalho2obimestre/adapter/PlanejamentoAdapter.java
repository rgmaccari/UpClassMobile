package com.example.trabalho2obimestre.adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.controller.PlanejamentoController;
import com.example.trabalho2obimestre.model.Planejamento;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.model.Planejamento;
import java.util.ArrayList;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.PlanejamentoViewHolder> {
    private ArrayList<Planejamento> listaPlanejamentos;

    // Construtor da classe
    public PlanejamentoAdapter(ArrayList<Planejamento> listaPlanejamentos) {
        this.listaPlanejamentos = listaPlanejamentos;
    }

    @NonNull
    @Override
    public PlanejamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planejamento, parent, false);
        return new PlanejamentoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanejamentoViewHolder holder, int position) {
        Planejamento planejamento = listaPlanejamentos.get(position);

        holder.edDescricao.setText(planejamento.getDescricao());
        holder.checkboxFeito.setChecked(planejamento.isCompleto());

        holder.checkboxFeito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            planejamento.setCompleto(isChecked);
        });

        holder.imgLixeira.setOnClickListener(v -> {
            listaPlanejamentos.remove(position);
            PlanejamentoController controller = new PlanejamentoController(v.getContext());
            controller.excluirPlanejamento(planejamento);
            notifyItemRemoved(position);
        });

        holder.edDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                planejamento.setDescricao(s.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlanejamentos.size();
    }

    public Planejamento addItem(int disciplinaId, int turmaId) {
        Planejamento planejamento = new Planejamento();
        planejamento.setDisciplinaId(disciplinaId);
        planejamento.setTurmaId(turmaId);

        listaPlanejamentos.add(planejamento);
        notifyItemInserted(listaPlanejamentos.size() - 1);
        return planejamento;
    }

    // Método para acessar a lista de planejamentos
    public ArrayList<Planejamento> getListaPlanejamentos() {
        return listaPlanejamentos;
    }

    public static class PlanejamentoViewHolder extends RecyclerView.ViewHolder {
        EditText edDescricao;
        CheckBox checkboxFeito;
        ImageView imgLixeira;

        public PlanejamentoViewHolder(View itemView) {
            super(itemView);
            edDescricao = itemView.findViewById(R.id.edDescricao);
            checkboxFeito = itemView.findViewById(R.id.checkboxFeito);
            imgLixeira = itemView.findViewById(R.id.imgLixeira);
        }
    }

    public void updatePlanejamentosAdapter(ArrayList<Planejamento> novosPlanejamentos) {
        this.listaPlanejamentos = novosPlanejamentos;
        notifyDataSetChanged();
    }
}
