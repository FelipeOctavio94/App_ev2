package cl.carreno.app_ev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.carreno.app_ev2.model.Usuario;

public class RegistrarActivity extends AppCompatActivity {
    EditText txt_email, txt_nombre,txt_celular, txt_password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        txt_email = findViewById(R.id.register_txt_email);
        txt_nombre = findViewById(R.id.register_txt_nombre);
        txt_celular = findViewById(R.id.register_txt_numero);
        txt_password = findViewById(R.id.register_txt_password);
        auth = FirebaseAuth.getInstance();
    }

    public void crearCuenta(View view) {
        final String email,nombre,celular,password;
        email = txt_email.getText().toString();
        nombre = txt_nombre.getText().toString();
        celular = txt_celular.getText().toString();
        password = txt_password.getText().toString();
        if(email.isEmpty() || nombre.isEmpty() || celular.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Los campos están vacíos",Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegistrarActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Usuarios");
                                Usuario user = new Usuario();
                                user.setNombre(nombre);
                                user.setCelular(celular);
                                user.setEmail(email);
                                user.setUserid(task.getResult().getUser().getUid());
                                myRef.push().setValue(user);
                                Toast.makeText(RegistrarActivity.this,"Cuenta creada con exito",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegistrarActivity.this,"No se pudo crear la cuenta",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}