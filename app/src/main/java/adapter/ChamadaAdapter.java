package adapter;
import com.example.trabalho2obimestre.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;

import java.util.List;
import model.ItemChamada;

public class ChamadaAdapter extends RecyclerView.Adapter<ChamadaAdapter.ViewHolder> {
    private List<ItemChamada> alunos;

    public ChamadaAdapter(List<ItemChamada> alunos) {
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
        holder.tvNome.setText(item.getTvNome());
        holder.tvRa.setText(item.getAlunoRa());
        holder.checkboxPresenca.setChecked(item.isCheckboxPresenca());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvRa;
        CheckBox checkboxPresenca;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvRa = itemView.findViewById(R.id.alunoRa);
            checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
        }
    }
}
