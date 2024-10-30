package com.example.trabalho2obimestre;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import helper.SQLiteDataHelper;

public class MainActivity extends AppCompatActivity {

    private ImageView btnChamada;
    private ImageView btnCriar;
    private ImageView btnCorrigir;
    private ImageView btnMedias;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnChamada = findViewById(R.id.btnChamada);
        btnCriar = findViewById(R.id.btnCriar);
        btnCorrigir = findViewById(R.id.btnCorrigir);
        btnMedias = findViewById(R.id.btnMedias);
        btnSair = findViewById(R.id.btnSair);


        btnChamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChamadaActivity.class);
                startActivity(intent);
            }
        });

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CriarActivity.class);
                startActivity(intent);
            }
        });

        btnCorrigir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CorrigirActivity.class);
                startActivity(intent);
            }
        });

        btnMedias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MediasActivity.class);
                startActivity(intent);
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });





    }
}