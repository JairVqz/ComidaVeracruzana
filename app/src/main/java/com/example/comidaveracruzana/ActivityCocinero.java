package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class ActivityCocinero extends AppCompatActivity {

    private Button mBtnCerrarSesion;
    private TextView mTvUsuario;
    private FirebaseAuth mAuth;
    private DatabaseReference bd_comidaveracruzana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocinero);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //loadFragment(inicioC);

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

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.inicioCo:
                    Intent intentInicio = new Intent(ActivityCocinero.this, ActivityCocinero.class);
                    startActivity(intentInicio);
                    //loadFragment(inicioC);
                    return true;
                case R.id.buscarCo:
                    Intent intentBuscar = new Intent(ActivityCocinero.this, BuscarCocinero.class);
                    startActivity(intentBuscar);
                    //loadFragment(buscarC);
                    return true;
                case R.id.sugerenciaCo:
                    Intent intentSugerencia = new Intent(ActivityCocinero.this, SugerenciaCocinero.class);
                    startActivity(intentSugerencia);
                    //loadFragment(sugerir);
                    return true;
                case R.id.cerrarCo:
                    Intent intentCerrar = new Intent(ActivityCocinero.this, CerrarCocinero.class);
                    startActivity(intentCerrar);
                    //loadFragment(cerrar);
                    return true;
            }
            return false;
        }
    };

    //esto remplaza el fragmento en caso de seleccionar otro
    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

}