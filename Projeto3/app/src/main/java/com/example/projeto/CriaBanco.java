package com.example.projeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CriaBanco {
    private SQLiteDatabase db;
    private BancoController banco;

    public CriaBanco(Context context) {
        banco = new BancoController(context);
    }

    // Função para incluir um novo usuário (tela de Cadastre_se)
    public String gravaUsuario(String _Nome, String _Email, String _Cpf, String _Senha) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nome", _Nome);
        valores.put("email", _Email);
        valores.put("cpf", _Cpf);
        valores.put("senha", _Senha);

        resultado = db.insert("usuarios", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao tentar efetuar o Cadastre_se";
        else
            return "Cadastro efetuado com sucesso";
    }

    // Função para registrar uma transação de Compra, Venda ou Aluguel
    public String insereTransacao(String _descricao, String _preco, String _tipoTransacao, String _metodoPagamento, String _email) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("descricao", _descricao);
        valores.put("preco", _preco);
        valores.put("tipoTransacao", _tipoTransacao); // Pode ser "Compra", "Venda" ou "Aluguel"
        valores.put("metodoPagamento", _metodoPagamento); // Pode ser "Cartao" ou "Pix"
        valores.put("email", _email);

        resultado = db.insert("transacoes", null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao registrar a transação";
        else
            return "Transação registrada com sucesso";
    }

    // Função para consultar transações por email
    public Cursor consultaTransacoesPorEmail(String _email) {
        Cursor cursor;
        String[] campos = { "idTransacao", "descricao", "preco", "tipoTransacao", "metodoPagamento", "email" };
        String where = "email = '" + _email + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("transacoes", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    // Função para verificar se usuário e senha existem (tela de Login)
    public Cursor ProcuraDadosLogin(String _email, String _senha) {
        Cursor cursor;
        String[] campos = { "idUsuario", "nome", "email", "cpf", "senha" };
        String where = "email = '" + _email + "' and senha = '" + _senha + "'";
        db = banco.getReadableDatabase();
        cursor = db.query("usuarios", campos, where, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    // Função para atualizar os dados de uma transação (exemplo: mudança de tipo de pagamento ou descrição)
    public String atualizaTransacao(int id, String descricao, String preco, String tipoTransacao, String metodoPagamento) {
        String msg = "Transação atualizada com sucesso!!!";
        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("descricao", descricao);
        valores.put("preco", preco);
        valores.put("tipoTransacao", tipoTransacao);
        valores.put("metodoPagamento", metodoPagamento);

        String condicao = "idTransacao = " + id;
        int linhas = db.update("transacoes", valores, condicao, null);

        if (linhas < 1) {
            msg = "Erro ao atualizar a transação";
        }

        db.close();
        return msg;
    }

    // Função para excluir uma transação
    public String excluirTransacao(int id) {
        String msg = "Transação excluída";
        db = banco.getWritableDatabase();

        String condicao = "idTransacao = " + id;
        int linhas = db.delete("transacoes", condicao, null);

        if (linhas < 1) {
            msg = "Erro ao excluir a transação";
        }

        db.close();
        return msg;
    }

    // Função para excluir um usuário
    public String excluirUsuario(int id) {
        String msg = "Usuário excluído";
        db = banco.getWritableDatabase();

        String condicao = "idUsuario = " + id;
        int linhas = db.delete("usuarios", condicao, null);

        if (linhas < 1) {
            msg = "Erro ao excluir o usuário";
        }

        db.close();
        return msg;
    }

    // Função que retorna o banco em modo de escrita
    public SQLiteDatabase getWritableDatabase() {
        return banco.getWritableDatabase();
    }
}