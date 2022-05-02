package com.example.comidaveracruzana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        //Agregar Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView ed_text = findViewById(R.id.tv_titulo);
        ImageView im_logo = findViewById(R.id.iv_logo);
        ImageView im_launch = findViewById(R.id.iv_launch);

        ed_text.setAnimation(animacion2);
        im_logo.setAnimation(animacion2);
        im_launch.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        }, 5000);

    }
}