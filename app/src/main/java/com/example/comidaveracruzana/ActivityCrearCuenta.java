package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ActivityCrearCuenta extends AppCompatActivity {

    private EditText et_usuario;
    private EditText et_correo;
    private EditText et_password;
    private EditText et_validarpassword;
    private Button btnRegistrar;

    //VARIABLES DE LOS DATOS QUE VAMOS A REGISTRAR
    private String usuario = "";
    private String correo = "";
    private String password = "";
    private String validarPassword = "";

    private FirebaseAuth mAuth;
    private DatabaseReference bd_comidaveracruzana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        bd_comidaveracruzana = FirebaseDatabase.getInstance().getReference("ComidaVeracruzana");

        et_usuario = (EditText) findViewById(R.id.tpRegistro_usuario);
        et_correo = (EditText) findViewById(R.id.tpRegistro_correo);
        et_password = (EditText) findViewById(R.id.tpRegistro_password);
        et_validarpassword = (EditText) findViewById(R.id.tpRegistro_validarPassword);
        btnRegistrar = (Button) findViewById(R.id.btnCrearCuenta);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = et_usuario.getText().toString();
                correo = et_correo.getText().toString().trim();
                password = et_password.getText().toString();
                validarPassword = et_validarpassword.getText().toString();

                if (!usuario.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !validarPassword.isEmpty()){
                    if (password.equals(validarPassword)){
                        if (password.length() >= 6){
                            registerUser();
                        }else{
                            Toast.makeText(ActivityCrearCuenta.this, "La contraseña debe tener al menos 6 carácteres", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(ActivityCrearCuenta.this, "Error las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActivityCrearCuenta.this, "Debes completar los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void registerUser(){
        mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> user = new HashMap<>();
                    user.put("nombre", usuario);
                    user.put("correo", correo);
                    user.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    //Usuario user = new Usuario(_id,usuario,password);
                    //String id = auth.getCurrentUser().getUid();
                    bd_comidaveracruzana.child("Usuarios").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                Toast.makeText(ActivityCrearCuenta.this, "La cuenta se creó correctamente", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ActivityCrearCuenta.this, ActivityLogin.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(ActivityCrearCuenta.this, "No se cargaron los datos correctamente", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else{
                    Toast.makeText(ActivityCrearCuenta.this, "Error, el registro falló", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}