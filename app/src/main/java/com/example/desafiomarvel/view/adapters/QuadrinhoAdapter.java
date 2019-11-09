package com.example.desafiomarvel.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.view.interfaces.ComicsOnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuadrinhoAdapter extends RecyclerView.Adapter<QuadrinhoAdapter.ViewHolder> {
    private List<Result> listaDeQuadrinhos;
    private ComicsOnClick listener;

    public QuadrinhoAdapter(List<Result> listaDeQuadrinhos, ComicsOnClick listener) {
        this.listaDeQuadrinhos = listaDeQuadrinhos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quadrinhos, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = listaDeQuadrinhos.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(view ->{
            listener.OnClick(result);
        });

    }

    @Override
    public int getItemCount() {
        return listaDeQuadrinhos.size();
    }

    public void atualizaLista(List<Result> novaLista){
        this.listaDeQuadrinhos.clear();
        this.listaDeQuadrinhos = novaLista;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoQadrinho;
        private TextView numQuadrinho;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoQadrinho = itemView.findViewById(R.id.imgQuadrinho);
            numQuadrinho = itemView.findViewById(R.id.numQuadrinho);
        }

        public void onBind(Result result){
            Picasso.get()
                    .load(result.getThumbnail().getPath()+ ".jpg")
                    .into(fotoQadrinho);
            numQuadrinho.setText("#" + result.getId());
        }

    }
}
