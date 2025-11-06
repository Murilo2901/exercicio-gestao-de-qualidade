package org.example.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://localhost:3306/Manutencao_industrial?useSSL=false&serverTimezone=UTC";
    private static final String USER ="root";
    private static final String SENHA ="";

    public static Connection conectar()throws SQLException {
        return DriverManager.getConnection(URL,USER,SENHA);
    }
}
