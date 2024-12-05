package com.example.projeto;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Configuracoes extends AppCompatActivity {

    private Button btAlterarSenha, btNotificacoes, btPrivacidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        // Inicializando os botões
        btAlterarSenha = findViewById(R.id.btAlterarSenha);
        btNotificacoes = findViewById(R.id.btNotificacoes);
        btPrivacidade = findViewById(R.id.btPrivacidade);

        // Configurando eventos de clique
        btAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para alterar a senha
            }
        });

        btNotificacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para configurar as notificações
            }
        });

        btPrivacidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para configurar privacidade
            }
        });
    }
}
