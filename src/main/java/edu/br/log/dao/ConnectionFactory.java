package edu.br.log.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/estoque_db?useSSL=false&allowPublicKey" +
            "Retrieval=true&serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";
    private ConnectionFactory(){}
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL,USER,PASS);
    }



}
