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
import cl.carreno.app_ev2.model.Denuncia;


public class DenunciasFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<Denuncia> list;
    RecyclerView recyclerView;
    String uid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denuncias, container, false);
        recyclerView = view.findViewById(R.id.recycler_denuncias);
        auth = FirebaseAuth.getInstance();
        list = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        uid = auth.getUid();
        myRef=database.getReference("denuncias");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    list.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        for (DataSnapshot ds_denuncias: ds.getChildren()){
                            Denuncia denuncias = ds_denuncias.getValue(Denuncia.class);
                            denuncias.setId(ds_denuncias.getKey());
                            list.add(denuncias);
                        }
                    }
                    LinearLayoutManager lm = new LinearLayoutManager(getActivity());
                    lm.setOrientation(RecyclerView.VERTICAL);
                    Adapter adapter = new Adapter(getActivity(), R.layout.item_denuncias,list);

                    recyclerView.setLayoutManager(lm);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    }
}