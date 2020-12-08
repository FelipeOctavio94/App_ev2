package cl.carreno.app_ev2.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.carreno.app_ev2.R;
import cl.carreno.app_ev2.model.Denuncia;


public class DenunciarFragment extends Fragment {
    EditText txt_titulo, txt_direccion;
    Button button_crear;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_denunciar, container, false);

        txt_titulo = view.findViewById(R.id.nuevo_titulo);
        txt_direccion = view.findViewById(R.id.nuevo_direccion);
        auth = FirebaseAuth.getInstance();
        button_crear = view.findViewById(R.id.nuevo_bt_crearDenuncia);

        button_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txt_titulo.getText().toString();
                String direccion = txt_direccion.getText().toString();
                String uid = auth.getCurrentUser().getUid();

                if (!titulo.isEmpty() && !direccion.isEmpty()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("denuncia").child(uid);
                    Denuncia denuncias = new Denuncia();
                    denuncias.setTitulo(titulo);
                    denuncias.setDireccion(direccion);
                    denuncias.setEstado("1");
                    myRef.push().setValue(denuncias);
                    Toast.makeText(getActivity(),"Denuncia creada con exito",Toast.LENGTH_SHORT).show();
                    txt_titulo.setText("");
                    txt_direccion.setText("");

                }else {
                    Toast.makeText(getActivity(),"Campos vacios, por favor ingrese los datos",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;

    }
}