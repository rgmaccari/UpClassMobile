package adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho2obimestre.R;

import java.util.ArrayList;
import java.util.List;
import model.Aluno;
public class ChamadaAdapter extends RecyclerView.Adapter<ChamadaAdapter.ViewHolder> {
    private ArrayList<Aluno> listaAlunos;
    private ArrayList<Aluno> listaAlunosOriginal;
    private Context context;

    public ChamadaAdapter(ArrayList<Aluno> listaAlunos, Context context) {
        this.listaAlunos = listaAlunos;
        this.listaAlunosOriginal = new ArrayList<>();
        this.context = context;
    }

    public void setListaAlunosOriginal(ArrayList<Aluno> listaAlunosOriginal) {
        this.listaAlunosOriginal = listaAlunosOriginal;
    }

    public void filtrarPorSerie(String serie) {
        listaAlunos.clear();
        for (Aluno aluno : listaAlunosOriginal) {
            if (aluno.getTurma().name().equalsIgnoreCase(serie)) {
                listaAlunos.add(aluno);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemList = inflater.inflate(R.layout.item_chamada, parent, false);
        return new ViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);
        holder.alunoRa.setText(String.valueOf(aluno.getRA()));
        holder.tvNome.setText(aluno.getNome());
        holder.checkboxPresenca.setChecked(aluno.isPresenca());
    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alunoRa;
        public TextView tvNome;
        public CheckBox checkboxPresenca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alunoRa = itemView.findViewById(R.id.alunoRa);
            tvNome = itemView.findViewById(R.id.tvNome);
            checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
        }
    }


}
