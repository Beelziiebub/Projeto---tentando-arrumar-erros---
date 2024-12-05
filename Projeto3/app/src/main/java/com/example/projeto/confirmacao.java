package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class confirmacao extends AppCompatActivity {

    // Variáveis para os elementos da tela
    private TextView txtConfirmacaoDescricao, txtConfirmacaoPreco,
            txtConfirmacaoTipoTransacao, txtConfirmacaoMetodoPagamento;
    private Button btConfirmar, btCancelar;

    // Recebe as informações da transação passadas pela tela anterior
    private String descricao, preco, tipoTransacao, metodoPagamento, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao);

        // Recupera as informações passadas pela tela anterior
        Intent intent = getIntent();
        descricao = intent.getStringExtra("descricao");
        preco = intent.getStringExtra("preco");
        tipoTransacao = intent.getStringExtra("tipoTransacao");
        metodoPagamento = intent.getStringExtra("metodoPagamento");
        email = intent.getStringExtra("email");

        // Inicializa os elementos da tela
        txtConfirmacaoDescricao = findViewById(R.id.txtConfirmacaoDescricao);
        txtConfirmacaoPreco = findViewById(R.id.txtConfirmacaoPreco);
        txtConfirmacaoTipoTransacao = findViewById(R.id.txtConfirmacaoTipoTransacao);
        txtConfirmacaoMetodoPagamento = findViewById(R.id.txtConfirmacaoMetodoPagamento);
        btConfirmar = findViewById(R.id.btConfirmar);
        btCancelar = findViewById(R.id.btCancelar);

        // Exibe as informações na tela de confirmação
        txtConfirmacaoDescricao.setText("Descrição: " + descricao);
        txtConfirmacaoPreco.setText("Preço: " + preco);
        txtConfirmacaoTipoTransacao.setText("Tipo de Transação: " + tipoTransacao);
        txtConfirmacaoMetodoPagamento.setText("Método de Pagamento: " + metodoPagamento);

        // Ação para o botão Confirmar
        btConfirmar.setOnClickListener(v -> {
            // Registra a transação no banco de dados
            BancoController bancoController = new BancoController(getBaseContext());
            String resultado = String.valueOf(bancoController.inserirTransacao(descricao, preco, tipoTransacao, metodoPagamento, email));

            // Exibe uma mensagem de sucesso ou erro
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();

            // Retorna à tela principal ou ao menu
            if (resultado.contains("sucesso")) {
                Intent telaMenu = new Intent(confirmacao.this, MainActivity.class);
                startActivity(telaMenu);
            }
        });

        // Ação para o botão Cancelar
        btCancelar.setOnClickListener(v -> {
            // Cancela e volta à tela anterior
            finish();
        });
    }
}

