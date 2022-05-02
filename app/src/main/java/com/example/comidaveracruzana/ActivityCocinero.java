package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityCocinero extends AppCompatActivity {

    private Button mBtnCerrarSesion;
    private TextView mTvUsuario;

    private FirebaseAuth mAuth;
    private DatabaseReference bd_comidaveracruzana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocinero);

        mAuth = FirebaseAuth.getInstance();
        bd_comidaveracruzana = FirebaseDatabase.getInstance().getReference("ComidaVeracruzana");

        mBtnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);
        mTvUsuario = (TextView) findViewById(R.id.tv_usuario);

        mBtnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(ActivityCocinero.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
        obtenerNombreUsuario();
    }

    public void obtenerNombreUsuario(){
        String id = mAuth.getCurrentUser().getUid();
        bd_comidaveracruzana.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String usuario = snapshot.child("nombre").getValue().toString();

                    mTvUsuario.setText(usuario);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}