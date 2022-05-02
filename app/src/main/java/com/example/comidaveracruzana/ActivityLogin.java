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

public class ActivityLogin extends AppCompatActivity {

    private EditText et_Correo;
    private EditText et_Password;
    private Button mBtnIniciarSesion;

    private String correo = "";
    private String password = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        et_Correo = (EditText) findViewById(R.id.pt_correo);
        et_Password = (EditText) findViewById(R.id.pt_password);
        mBtnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        mBtnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = et_Correo.getText().toString().trim();
                password = et_Password.getText().toString();

                if (!correo.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(ActivityLogin.this, "Complete los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void crearCuenta(View view){
        Intent intent = new Intent(ActivityLogin.this, ActivityCrearCuenta.class);
        startActivity(intent);
    }

    public void loginUser(){
        mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(correo.equals("admin@administrador.com")){
                        Intent intent = new Intent(ActivityLogin.this, ActivityAdministrador.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(ActivityLogin.this, ActivityCocinero.class);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(ActivityLogin.this, "Error al iniciar sesión verifica los campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();



        if (mAuth.getCurrentUser() != null && !mAuth.getCurrentUser().equals("Administrador")){
            Intent intent = new Intent(ActivityLogin.this, ActivityCocinero.class);
            startActivity(intent);
            finish();
        }else if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().equals("Administrador")){
            Intent intent = new Intent(ActivityLogin.this, ActivityAdministrador.class);
            startActivity(intent);
            finish();
        }
    }

}