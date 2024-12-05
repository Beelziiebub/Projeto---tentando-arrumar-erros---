package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnWelcome;
    private TextView welcomeText;

    @SuppressLint({"MissingInflatedId", "WrongViewCast", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Layout da tela de boas-vindas

        // Inicializar os elementos
        btnWelcome = findViewById(R.id.btnWelcome);  // Certifique-se de que o ID é o correto para o botão
        welcomeText = findViewById(R.id.welcomeText);  // Certifique-se de que o ID é o correto para o TextView

        // Configurar o clique do botão
        btnWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ao clicar, navega para a tela de login
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
