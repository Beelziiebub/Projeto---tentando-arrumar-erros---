package com.example.projeto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastre_se extends AppCompatActivity implements View.OnClickListener {

    Button btCADSalvar;
    EditText txtCADNome, txtCADEmail, txtCADCpf, txtCADSenha, txtCADSenha2;
    String txtNome, txtEmail, txtCpf, txtSenha, txtSenha2;

    // Mensagens de erro
    private static final String ERRO_NOME = "Atenção - O campo NOME deve ser preenchido!";
    private static final String ERRO_EMAIL = "Atenção - O campo E-MAIL deve ser preenchido!";
    private static final String ERRO_CPF = "Atenção - O campo CPF deve ser preenchido!";
    private static final String ERRO_SENHA = "Atenção - O campo SENHA deve ser preenchido!";
    private static final String ERRO_SENHA2 = "Atenção - O campo CONFIRMAÇÃO DE SENHA deve ser preenchido!";
    private static final String ERRO_SENHA_NAO_COINCIDE = "Atenção - Os campos Senha e Confirma Senha não são iguais";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastre_se);

        // Inicializando os campos
        btCADSalvar = findViewById(R.id.btCADSalvar);
        txtCADNome = findViewById(R.id.txtCADNome);
        txtCADEmail = findViewById(R.id.txtCADEmail);
        txtCADCpf = findViewById(R.id.txtCADCpf);
        txtCADSenha = findViewById(R.id.txtCADSenha);
        txtCADSenha2 = findViewById(R.id.txtCADSenha2);

        btCADSalvar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Coleta os dados dos campos
        txtNome = txtCADNome.getText().toString();
        txtEmail = txtCADEmail.getText().toString();
        txtCpf = txtCADCpf.getText().toString();
        txtSenha = txtCADSenha.getText().toString();
        txtSenha2 = txtCADSenha2.getText().toString();

        // Verifica se há algum erro
        if (!verificaDados()) {
            // Gravar os dados no banco
            BancoController bd = new BancoController(getBaseContext());
            String resultado = String.valueOf(bd.gravaUsuario(txtNome, txtEmail, txtCpf, txtSenha));
            // Exibe o resultado
            showToast(resultado);
        }
    }

    public boolean verificaDados() {
        // Validações dos campos
        if (txtNome.isEmpty()) {
            showToast(ERRO_NOME);
            return true;
        }
        if (txtEmail.isEmpty()) {
            showToast(ERRO_EMAIL);
            return true;
        }
        if (txtCpf.isEmpty()) {
            showToast(ERRO_CPF);
            return true;
        }
        if (txtSenha.isEmpty()) {
            showToast(ERRO_SENHA);
            return true;
        }
        if (txtSenha2.isEmpty()) {
            showToast(ERRO_SENHA2);
            return true;
        }
        if (!txtSenha.equals(txtSenha2)) {
            showToast(ERRO_SENHA_NAO_COINCIDE);
            return true;
        }
        return false;
    }

    // Método para exibir toasts
    private void showToast(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }
}

