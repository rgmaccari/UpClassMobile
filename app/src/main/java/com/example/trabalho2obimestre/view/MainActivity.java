package com.example.trabalho2obimestre.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2obimestre.R;

import com.example.trabalho2obimestre.helper.SQLiteDataHelper;

public class MainActivity extends AppCompatActivity {

    private ImageView btnChamada;
    private ImageView btnCriar;
    private ImageView btnCorrigir;
    private ImageView btnMedias;
    private Button btnSair;
    private SQLiteDataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Abrir a DataBase:
        dbHelper = new SQLiteDataHelper(this, "ALUNO.db", null, 1);

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