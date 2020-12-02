package cl.carreno.app_ev2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.carreno.app_ev2.R;
import cl.carreno.app_ev2.model.Denuncia;

public class Adapter extends RecyclerView.Adapter<Adapter.DenunciaHolder>{
    private Activity activity;
    private int layout;
    private List<Denuncia> list;

    public Adapter(Activity activity, int layout, List<Denuncia> denuncia) {
        this.activity = activity;
        this.layout = layout;
        this.list = denuncia;
    }

    @NonNull
    @Override
    public DenunciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new DenunciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DenunciaHolder holder, int position) {
        Denuncia denuncia = list.get(position);
        holder.titulo.setText(denuncia.getTitulo());
        holder.direccion.setText(denuncia.getDireccion());
        holder.id = denuncia.getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{
        TextView titulo, direccion;
        String id;

        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);
            titulo =  itemView.findViewById(R.id.item_denuncia_title);
            direccion = itemView.findViewById(R.id.item_denuncia_direccion);
        }
    }


}
