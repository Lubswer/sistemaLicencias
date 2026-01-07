package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://yamanote.proxy.rlwy.net:17281/railway";
        String nombre = "root";
        String clave = "KoNkrMRyFloGcmbFtuZwlMJnSSIfbTui";
        return DriverManager.getConnection(url,nombre,clave);
    }
}
