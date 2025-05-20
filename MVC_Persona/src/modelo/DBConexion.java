package modelo;

import java.sql.*;

public class DBConexion {
    // Constantes de conexion a BD
    private static final String BD_URL = "jdbc:mysql://localhost/bd_personas";
    private static final String BD_USER = "root";
    private static final String BD_PASS = "rm2223";
    
    // Métodos de clase
    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(BD_URL, BD_USER, BD_PASS);
            System.out.println("Conectado a la BD");

        } catch (SQLException ex) {
            System.out.println("Error al conectar a la BD");
            System.out.println(ex.getMessage());

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return conexion;
    }
}
