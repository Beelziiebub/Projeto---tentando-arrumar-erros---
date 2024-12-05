
package com.example.projeto;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ConsultarContato extends AppCompatActivity {

    private ListView listViewContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_contato);

        // Inicializando a ListView
        listViewContatos = findViewById(R.id.listViewContatos);

        // Dados fictícios para exibição
        String[] contatos = {
                "João Silva - 1234-5678",
                "Maria Souza - 2345-6789",
                "Carlos Pereira - 3456-7890",
                "Ana Oliveira - 4567-8901"
        };

        // Criando um adaptador para a lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
        listViewContatos.setAdapter(adapter);
    }
}

