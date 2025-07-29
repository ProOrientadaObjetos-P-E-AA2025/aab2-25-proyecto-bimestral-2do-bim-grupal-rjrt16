package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:sqlite:D:/Poo/aab2-25-proyecto-bimestral-2do-bim-grupal-rjrt16/2_SOLUCION/proyectoBimestral2/Cliente.db";

    public static Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("si funca.");
        } catch (SQLException e) {
            System.err.println(" No funca: " + e.getMessage());
        }
        return conn;
    }
 
}



