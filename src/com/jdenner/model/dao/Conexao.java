package com.jdenner.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String SERVIDOR = "localhost";
    private final String PORTA = "3306";
    private final String USUARIO = "root";
    private final String SENHA = "root";
    private final String BANCO = "db_pessoa";
    private final String DRIVER = "com.mysql.jdbc.Driver";

    private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BANCO;

    private Connection CONEXAO;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        CONEXAO = DriverManager.getConnection(URL, USUARIO, SENHA);
        CONEXAO.setAutoCommit(false);
    }

    public Connection getConexao() {
        return CONEXAO;
    }

    public void confirmar() throws SQLException {
        CONEXAO.commit();
        CONEXAO.close();
    }

    public void fechar() {
        try {
            CONEXAO.rollback();
            CONEXAO.close();
        } catch (Exception e) {
        }
    }

}
