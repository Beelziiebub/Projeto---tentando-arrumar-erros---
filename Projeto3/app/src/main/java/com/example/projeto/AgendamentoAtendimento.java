package com.example.projeto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AgendamentoAtendimento extends AppCompatActivity implements View.OnClickListener {

    // Declarando os elementos da interface
    private DatePicker txtAGEData;
    private Spinner txtAGETipoAtendimento, txtAGEHora;
    private EditText txtAGEObservacoes;
    private Button btAGEAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento_atendimento);

        // Inicializando os componentes da interface
        txtAGEData = findViewById(R.id.txtAGEData);
        txtAGETipoAtendimento = findViewById(R.id.txtAGETipoAtendimento);
        txtAGEHora = findViewById(R.id.txtAGEHora);
        txtAGEObservacoes = findViewById(R.id.txtAGEObservacoes);
        btAGEAgendar = findViewById(R.id.btAGEAgendar);

        // Configurando o ouvinte de clique para o botão "Agendar"
        btAGEAgendar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Validar os dados preenchidos pelo usuário
        if (v.getId() == R.id.btAGEAgendar) {
            if (validateFields()) {
                // Coletando os dados inseridos
                int year = txtAGEData.getYear();
                int month = txtAGEData.getMonth();
                int day = txtAGEData.getDayOfMonth();

                @SuppressLint("DefaultLocale") String dataSelecionada = String.format("%02d/%02d/%d", day, month + 1, year);
                String tipoAtendimento = txtAGETipoAtendimento.getSelectedItem().toString();
                String horaSelecionada = txtAGEHora.getSelectedItem().toString();
                String observacoes = txtAGEObservacoes.getText().toString();

                // Exibir a confirmação do agendamento
                String mensagem = String.format("Atendimento agendado com sucesso para %s às %s. Tipo: %s.\nObservações: %s",
                        dataSelecionada, horaSelecionada, tipoAtendimento, observacoes);
                Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

                // Aqui você pode adicionar lógica para salvar as informações no banco de dados ou processá-las de outra forma
            }
        }
    }

    // Método para validar os campos do formulário
    private boolean validateFields() {
        if (txtAGEData.getDayOfMonth() == 0) {
            Toast.makeText(getApplicationContext(), "Atenção: A data é obrigatória.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (txtAGETipoAtendimento.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Atenção: Selecione o tipo de atendimento.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (txtAGEHora.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Atenção: Selecione a hora do atendimento.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
