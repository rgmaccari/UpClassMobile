package com.example.trabalho2obimestre.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho2obimestre.R;

public class MainActivity extends AppCompatActivity {

    private ImageView btnChamada;
    private ImageView btnPlanejamento;
    private ImageView btnMedias;
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);        setContentView(R.layout.activity_main);

        btnChamada = findViewById(R.id.btnChamada);
        btnPlanejamento = findViewById(R.id.btnPlanejamento);
        btnMedias = findViewById(R.id.btnMedias);
        btnSair = findViewById(R.id.btnSair);


        btnChamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChamadaActivity.class);
                startActivity(intent);
            }
        });

        btnPlanejamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlanejamentoActivity.class);
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