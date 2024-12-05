package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Compra_venda_aluga extends AppCompatActivity implements View.OnClickListener {

    // Definindo os componentes da tela
    private Button btnFinalizarCompra;
    private EditText edtDescricaoProduto, edtPrecoProduto, edtNumeroCartao, edtValidadeCartao, edtCvvCartao, edtChavePix;
    private RadioGroup radioGroupTransacao, radioGroupPagamento;
    private LinearLayout layoutCartao, layoutPix;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compra_venda_aluga);  // Aqui é o layout que contém o XML

        // Aplicando Padding para os system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TelaCompraVendaAluguel), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializando os componentes
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);
        edtDescricaoProduto = findViewById(R.id.edtDescricaoProduto);
        edtPrecoProduto = findViewById(R.id.edtPrecoProduto);
        edtNumeroCartao = findViewById(R.id.edtNumeroCartao);
        edtValidadeCartao = findViewById(R.id.edtValidadeCartao);
        edtCvvCartao = findViewById(R.id.edtCvvCartao);
        edtChavePix = findViewById(R.id.edtChavePix);

        radioGroupTransacao = findViewById(R.id.radioGroupTransacao);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);

        layoutCartao = findViewById(R.id.layoutCartao);
        layoutPix = findViewById(R.id.layoutPix);

        // Definindo onClickListeners
        btnFinalizarCompra.setOnClickListener(this);

        // Listener para alterar visibilidade dos layouts conforme método de pagamento
        radioGroupPagamento.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbCartao) {
                layoutCartao.setVisibility(View.VISIBLE);
                layoutPix.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbPix) {
                layoutCartao.setVisibility(View.GONE);
                layoutPix.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnFinalizarCompra) {
            if (VerificaDados()) {
                // Aqui você processaria a transação e mostraria uma mensagem de sucesso
                Toast.makeText(getApplicationContext(), "Transação Finalizada com Sucesso!", Toast.LENGTH_LONG).show();
                // Você pode fazer uma transição para a tela de confirmação, por exemplo
                Intent intent = new Intent(this, confirmacao.class);
                startActivity(intent);
            }
        }
    }

    private boolean VerificaDados() {
        // Verificando se a descrição do produto foi preenchida
        if (edtDescricaoProduto.getText().toString().isEmpty()) {
            edtDescricaoProduto.setError("Descrição do produto é obrigatória!");
            edtDescricaoProduto.requestFocus();
            return false;
        }

        // Verificando se o preço do produto é válido
        String preco = edtPrecoProduto.getText().toString();
        if (preco.isEmpty()) {
            edtPrecoProduto.setError("Preço do produto é obrigatório!");
            edtPrecoProduto.requestFocus();
            return false;
        }

        try {
            Double.parseDouble(preco); // Verificando se o preço é um número válido
        } catch (NumberFormatException e) {
            edtPrecoProduto.setError("Preço inválido!");
            edtPrecoProduto.requestFocus();
            return false;
        }

        // Verificando se o pagamento foi selecionado
        int selectedPaymentId = radioGroupPagamento.getCheckedRadioButtonId();
        if (selectedPaymentId == -1) {
            Toast.makeText(this, "Selecione um método de pagamento", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Se o pagamento for via Cartão
        if (selectedPaymentId == R.id.rbCartao) {
            // Verificando se o número do cartão foi preenchido corretamente
            if (edtNumeroCartao.getText().toString().isEmpty()) {
                edtNumeroCartao.setError("Número do cartão é obrigatório!");
                edtNumeroCartao.requestFocus();
                return false;
            }

            // Verificando validade e CVV do cartão
            if (edtValidadeCartao.getText().toString().isEmpty()) {
                edtValidadeCartao.setError("Validade do cartão é obrigatória!");
                edtValidadeCartao.requestFocus();
                return false;
            }

            if (edtCvvCartao.getText().toString().isEmpty()) {
                edtCvvCartao.setError("CVV do cartão é obrigatório!");
                edtCvvCartao.requestFocus();
                return false;
            }
        }

        // Se o pagamento for via Pix
        if (selectedPaymentId == R.id.rbPix) {
            // Verificando se a chave PIX foi inserida
            if (edtChavePix.getText().toString().isEmpty()) {
                edtChavePix.setError("Chave PIX é obrigatória!");
                edtChavePix.requestFocus();
                return false;
            }
        }

        return true; // Dados estão válidos
    }
}
