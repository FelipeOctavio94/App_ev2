package cl.carreno.app_ev2.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cl.carreno.app_ev2.R;
import cl.carreno.app_ev2.adapter.Adapter;
import cl.carreno.app_ev2.adapter.AdapterMisDenuncias;
import cl.carreno.app_ev2.model.Denuncia;


public class MisdenunciasFragment extends Fragment {
    FirebaseAuth auth;
    List<Denuncia> list;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_misdenuncias, container, false);
        auth = FirebaseAuth.getInstance();
        recyclerView = view.findViewById(R.id.recycler_misdenuncias);
        String uid = auth.getCurrentUser().getUid();
        list = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("denuncias").child(uid);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        Denuncia denuncia = ds.getValue(Denuncia.class);
                        denuncia.setId(ds.getKey());
                        list.add(denuncia);
                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(RecyclerView.VERTICAL);

                    AdapterMisDenuncias adapterMisDenuncias = new AdapterMisDenuncias(getActivity(),R.layout.item_misdenuncias,list);
                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapterMisDenuncias);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        return view;    }
}