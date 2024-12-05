package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton;
    private TextView forgotPassword, createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // Configura o layout da tela de login
            setContentView(R.layout.activity_login);
            Log.d("LoginActivity", "Layout carregado com sucesso.");

            // Inicializa os elementos da interface
            initializeUI();

            // Configura os listeners
            setupListeners();
        } catch (Exception e) {
            // Captura erros inesperados e notifica o usuário
            Log.e("LoginActivity", "Erro ao inicializar a Activity", e);
            Toast.makeText(this, "Erro ao carregar a tela. Verifique a configuração do aplicativo.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Inicializa os elementos da interface verificando se os IDs estão corretos no XML.
     */
    private void initializeUI() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.loginButton);
        forgotPassword = findViewById(R.id.forgotPassword);
        createAccount = findViewById(R.id.createAccount);

        // Verifica se todos os elementos foram encontrados corretamente
        if (editTextEmail == null || editTextPassword == null || loginButton == null ||
                forgotPassword == null || createAccount == null) {
            throw new IllegalStateException("Erro: Um ou mais IDs não foram encontrados no layout XML. Verifique os IDs.");
        }
    }

    /**
     * Configura os listeners para os botões e outros elementos interativos.
     */
    private void setupListeners() {
        // Listener para o botão de login
        loginButton.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            // Validações
            if (email.isEmpty()) {
                Toast.makeText(Login.this, "Por favor, insira o e-mail.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(email)) {
                Toast.makeText(Login.this, "E-mail inválido. Verifique o formato.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(Login.this, "Por favor, insira a senha.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simula uma validação de login
            if (email.equals("usuario@exemplo.com") && password.equals("senha123")) {
                Toast.makeText(Login.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "E-mail ou senha incorretos.", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener para o link "Esqueci a senha"
        forgotPassword.setOnClickListener(v ->
                Toast.makeText(Login.this, "Redirecionando para recuperação de senha...", Toast.LENGTH_SHORT).show()
        );

        // Listener para o link "Criar Conta"
        createAccount.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Cadastre_se.class);
            startActivity(intent);
        });
    }

    /**
     * Valida se o e-mail tem um formato correto.
     *
     * @param email o e-mail a ser validado.
     * @return true se o formato do e-mail for válido, false caso contrário.
     */
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
