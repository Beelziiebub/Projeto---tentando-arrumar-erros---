package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meuBanco.db";
    private static final int DATABASE_VERSION = 1;

    public BancoController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criar as tabelas do banco
        String sqlCreateUsuarios = "CREATE TABLE usuarios (" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                "email TEXT," +
                "cpf TEXT," +
                "senha TEXT)";

        String sqlCreateTransacoes = "CREATE TABLE transacoes (" +
                "idTransacao INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao TEXT," +
                "preco TEXT," +
                "tipoTransacao TEXT," +
                "metodoPagamento TEXT," +
                "email TEXT)";

        db.execSQL(sqlCreateUsuarios);
        db.execSQL(sqlCreateTransacoes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aqui você pode realizar migrações do banco de dados se necessário
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS transacoes");
        onCreate(db);
    }

    // Função para gravar o usuário no banco de dados
    public long gravaUsuario(String nome, String email, String cpf, String senha) {
        // Obtendo uma instância do banco de dados
        SQLiteDatabase db = this.getWritableDatabase();

        // Criando um objeto ContentValues para inserir os dados
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("email", email);
        values.put("cpf", cpf);
        values.put("senha", senha);

        // Inserindo os dados na tabela de usuários e retornando o id gerado
        long id = db.insert("usuarios", null, values);

        // Fechando o banco de dados
        db.close();

        return id; // Retorna o id do usuário inserido ou -1 se houver erro
    }

    // Função para inserir uma transação no banco de dados
    public long inserirTransacao(String descricao, String preco, String tipoTransacao, String metodoPagamento, String email) {
        // Obtendo uma instância do banco de dados
        SQLiteDatabase db = this.getWritableDatabase();

        // Criando um objeto ContentValues para inserir os dados
        ContentValues values = new ContentValues();
        values.put("descricao", descricao);
        values.put("preco", preco);
        values.put("tipoTransacao", tipoTransacao);
        values.put("metodoPagamento", metodoPagamento);
        values.put("email", email);

        // Inserindo os dados na tabela de transações e retornando o id gerado
        long id = db.insert("transacoes", null, values);

        // Fechando o banco de dados
        db.close();

        return id; // Retorna o id da transação inserida ou -1 se houver erro
    }

    // Essa função retorna o banco de dados no modo leitura e escrita
    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    // Exemplo de uso dos métodos gravaUsuario e inserirTransacao
    public static void main(String[] args) {
        // Suponhamos que o contexto esteja disponível (em uma Activity ou aplicação Android)
        Context context = null; // Substitua por um contexto válido, como 'this' em uma Activity.

        // Criando uma instância do BancoController
        BancoController bancoController = new BancoController(context);

        // Gravar um usuário no banco de dados
        long idUsuario = bancoController.gravaUsuario("João Silva", "joao@email.com", "12345678901", "senha123");

        // Verificar se o usuário foi gravado com sucesso
        if (idUsuario != -1) {
            System.out.println("Usuário gravado com sucesso! ID do Usuário: " + idUsuario);
        } else {
            System.out.println("Ocorreu um erro ao gravar o usuário.");
        }

        // Inserir uma transação no banco de dados
        long idTransacao = bancoController.inserirTransacao("Venda de Carro", "50000", "Venda", "Cartão de Crédito", "joao@email.com");

        // Verificar se a transação foi inserida com sucesso
        if (idTransacao != -1) {
            System.out.println("Transação inserida com sucesso! ID da Transação: " + idTransacao);
        } else {
            System.out.println("Ocorreu um erro ao inserir a transação.");
        }
    }
}