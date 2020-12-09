package cl.carreno.app_ev2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public Adapter(Activity activity, int layout, List<Denuncia> list) {
        this.activity = activity;
        this.layout = layout;
        this.list = list;
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


        int e = Integer.parseInt(denuncia.getEstado());
        if(e == 1){
            holder.item_estado.setImageResource(R.drawable.apagado);
        }
        if (e == 0 ){
            holder.item_estado.setImageResource(R.drawable.encendido);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DenunciaHolder extends RecyclerView.ViewHolder{

        public TextView titulo , direccion , id;
        ImageView item_estado;

        public DenunciaHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_denuncia);
            titulo =  itemView.findViewById(R.id.item_denuncia_title);
            direccion = itemView.findViewById(R.id.item_denuncia_direccion);
            item_estado = itemView.findViewById(R.id.item_estado);
        }
    }


}
