package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Administracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.inicioAdm:
                    Intent intentInicio = new Intent(Administracion.this, ActivityAdministrador.class);
                    startActivity(intentInicio);
                    //loadFragment(inicioA);
                    return true;
                case R.id.buscarAdm:
                    Intent intentBuscar = new Intent(Administracion.this, BuscarAdm.class);
                    startActivity(intentBuscar);
                    //loadFragment(buscarA);
                    return true;
                case R.id.administrarAdm:
                    Intent intentAdmi = new Intent(Administracion.this, Administracion.class);
                    startActivity(intentAdmi);
                    //loadFragment(adm);
                    return true;
                case R.id.sugerenciaAdm:
                    Intent intentSugerencia = new Intent(Administracion.this, SugerenciaAdm.class);
                    startActivity(intentSugerencia);
                    //loadFragment(sugerir);
                    return true;
                case R.id.cerrarAdm:
                    Intent intentCerrar = new Intent(Administracion.this, CerrarAdm.class);
                    startActivity(intentCerrar);
                    //loadFragment(cerrar);
                    return true;
            }
            return false;
        }
    };
}