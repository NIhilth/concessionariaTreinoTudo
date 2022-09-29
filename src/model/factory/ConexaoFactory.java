package model.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoFactory {
    private final String URL = "jdbc:mysql://localhost:3306/concessionaria";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection conectaBD()  {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception excecao) {
            excecao.printStackTrace();
        }
        throw new RuntimeException("Conexão em sí deu ruim");
    }
}
