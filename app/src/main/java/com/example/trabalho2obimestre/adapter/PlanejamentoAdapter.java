package com.example.trabalho2obimestre.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.model.Planejamento;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trabalho2obimestre.R;
import com.example.trabalho2obimestre.model.Planejamento;
import java.util.ArrayList;

public class PlanejamentoAdapter extends RecyclerView.Adapter<PlanejamentoAdapter.PlanejamentoViewHolder> {

    private ArrayList<Planejamento> listaPlanejamentos;

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
        holder.checkboxPresenca.setChecked(planejamento.isCompleto());

        holder.checkboxPresenca.setOnCheckedChangeListener((buttonView, isChecked) -> {
            planejamento.setCompleto(isChecked);
        });

        holder.imgLixeira.setOnClickListener(v -> {
            listaPlanejamentos.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaPlanejamentos.size();
    }

    public static class PlanejamentoViewHolder extends RecyclerView.ViewHolder {
        EditText edDescricao;
        CheckBox checkboxPresenca;
        ImageView imgLixeira;

        public PlanejamentoViewHolder(View itemView) {
            super(itemView);

            edDescricao = itemView.findViewById(R.id.edDescricao);
            checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
            imgLixeira = itemView.findViewById(R.id.imgLixeira);
        }
    }
}
