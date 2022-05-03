package com.example.comidaveracruzana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CerrarCocinero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_cocinero);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.inicioCo:
                    Intent intentInicio = new Intent(CerrarCocinero.this, ActivityCocinero.class);
                    startActivity(intentInicio);
                    //loadFragment(inicioC);
                    return true;
                case R.id.buscarCo:
                    Intent intentBuscar = new Intent(CerrarCocinero.this, BuscarCocinero.class);
                    startActivity(intentBuscar);
                    //loadFragment(buscarC);
                    return true;
                case R.id.sugerenciaCo:
                    Intent intentSugerencia = new Intent(CerrarCocinero.this, SugerenciaCocinero.class);
                    startActivity(intentSugerencia);
                    //loadFragment(sugerir);
                    return true;
                case R.id.cerrarCo:
                    Intent intentCerrar = new Intent(CerrarCocinero.this, CerrarCocinero.class);
                    startActivity(intentCerrar);
                    //loadFragment(cerrar);
                    return true;
            }
            return false;
        }
    };
}