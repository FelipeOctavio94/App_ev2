package cl.carreno.app_ev2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Objects;

import cl.carreno.app_ev2.R;
import cl.carreno.app_ev2.model.Denuncia;

public class AdapterMisDenuncias extends RecyclerView.Adapter<AdapterMisDenuncias.MisDenuciasHolder> {
    private int layout;
    public List<Denuncia> list;

    public AdapterMisDenuncias(Activity activity, int layout, List<Denuncia> list){
        this.layout = layout;
        this.list = list;
    }

    public class MisDenuciasHolder extends RecyclerView.ViewHolder {
        public TextView titulo , direccion , id;
        DatabaseReference reference;
        ImageView item_estado;

        public MisDenuciasHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_denuncia_id);
            titulo = itemView.findViewById(R.id.item_denuncia_title);
            direccion = itemView.findViewById(R.id.item_denuncia_direccion);
            item_estado = itemView.findViewById(R.id.item_estado);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            final String uid = Objects.requireNonNull(Objects.requireNonNull(user).getUid());
            reference = database.getReference("denuncias").child(uid);


        }
    }

    @NonNull
    @Override
    public MisDenuciasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        return new MisDenuciasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisDenuciasHolder holder, int position) {
        Denuncia denuncias = list.get(position);
        holder.id.setText(denuncias.getId());
        holder.titulo.setText(denuncias.getTitulo());
        holder.direccion.setText(denuncias.getDireccion());

        int e = Integer.parseInt(denuncias.getEstado());
        if (e == 1){
            holder.item_estado.setImageResource(R.drawable.apagado);
        }
        if (e == 0){
            holder.item_estado.setImageResource(R.drawable.encendido);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
