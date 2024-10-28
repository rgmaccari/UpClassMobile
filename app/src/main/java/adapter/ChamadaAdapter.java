package adapter;
import android.content.Context;
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

public class ChamadaAdapter extends RecyclerView.Adapter<ChamadaAdapter.ViewHolder>{
    private ArrayList<Aluno> listaAlunos;
    private Context context;

    // Construtor: recebe uma lista de alunos e pega um objeto Aluno para adicionar ao Adaptador
    public ChamadaAdapter(ArrayList<Aluno>listaAlunos, Context context){
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    //Carrega os elementos XML para item da lista:
    @NonNull
    @Override
    public ChamadaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemList = inflater.inflate(R.layout.item_chamada, parent, false);
        return new ViewHolder(itemList);
    }

    // Aciona os dados do aluno em tela:
    @Override
    public void onBindViewHolder(@NonNull ChamadaAdapter.ViewHolder holder, int position) {
        Aluno aluno = this.listaAlunos.get(position);
        holder.alunoRa.setText(String.valueOf(aluno.getRA()));
        holder.tvNome.setText(aluno.getNome());
        holder.checkboxPresenca.setChecked(aluno.isPresenca());
    }

    // Quantidade de elementos da lista.
    @Override
    public int getItemCount() {
        return this.listaAlunos.size();
    }

    //Vincular os elementos XML para cada posição da lista:
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alunoRa;
        public TextView tvNome;
        public CheckBox checkboxPresenca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.alunoRa = itemView.findViewById(R.id.alunoRa);
            this.tvNome = itemView.findViewById(R.id.tvNome);
            this.checkboxPresenca = itemView.findViewById(R.id.checkboxPresenca);
        }
    }
}
