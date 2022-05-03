package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityAdministrador extends AppCompatActivity {

    private Button mBtnCerrarSesion;
    private TextView mTvUsuario;
    private FirebaseAuth mAuth;
    private DatabaseReference bd_comidaveracruzana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mAuth = FirebaseAuth.getInstance();
        bd_comidaveracruzana = FirebaseDatabase.getInstance().getReference("ComidaVeracruzana");

        mBtnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesionAdmin);
        mTvUsuario = (TextView) findViewById(R.id.tv_usuarioAdministrador);

        mBtnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(ActivityAdministrador.this, ActivityLogin.class);
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

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.inicioAdm:
                    Intent intentInicio = new Intent(ActivityAdministrador.this, ActivityAdministrador.class);
                    startActivity(intentInicio);
                    //loadFragment(inicioA);
                    return true;
                case R.id.buscarAdm:
                    Intent intentBuscar = new Intent(ActivityAdministrador.this, BuscarAdm.class);
                    startActivity(intentBuscar);
                    //loadFragment(buscarA);
                    return true;
                case R.id.administrarAdm:
                    Intent intentAdmi = new Intent(ActivityAdministrador.this, Administracion.class);
                    startActivity(intentAdmi);
                    //loadFragment(adm);
                    return true;
                case R.id.sugerenciaAdm:
                    Intent intentSugerencia = new Intent(ActivityAdministrador.this, SugerenciaAdm.class);
                    startActivity(intentSugerencia);
                    //loadFragment(sugerir);
                    return true;
                case R.id.cerrarAdm:
                    Intent intentCerrar = new Intent(ActivityAdministrador.this, CerrarAdm.class);
                    startActivity(intentCerrar);
                    //loadFragment(cerrar);
                    return true;
            }
            return false;
        }
    };

}