package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    // Declaração dos botões
    private Button btAgendamento, btConsultarContatos, btConfiguracoes, btSair;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Defina o layout XML

        // Inicialização dos botões
        btAgendamento = findViewById(R.id.btAgendamento);
        btConsultarContatos = findViewById(R.id.btConsultarContatos);
        btConfiguracoes = findViewById(R.id.btConfiguracoes);
        btSair = findViewById(R.id.btSair);

        // Definindo os eventos de clique para os botões
        btAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para o botão Agendamento
                // Por exemplo, abrir uma nova Activity para agendamento
                Intent intent = new Intent(Menu.this, AgendamentoAtendimento.class);
                startActivity(intent);
            }
        });

        btConsultarContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para o botão Consultar Contatos
                // Exemplo de ação: abrir uma nova tela para consulta
                Intent intent = new Intent(Menu.this, ConsultarContato.class);
                startActivity(intent);
            }
        });

        btConfiguracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode adicionar a ação para o botão Configurações
                // Exemplo de ação: abrir a tela de configurações
                Intent intent = new Intent(Menu.this, Configuracoes.class);
                startActivity(intent);
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação para o botão Sair, que pode encerrar a aplicação
                finish();  // Encerra a atividade atual (ou a aplicação, se for a principal)
            }
        });
    }
}

