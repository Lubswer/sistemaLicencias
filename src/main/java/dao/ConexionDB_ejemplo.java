package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 - Archivo de ejemplo.
 - Copia este archivo como ConexionDB.java
 - y completa los datos de conexión.
 */
public class ConexionDB_ejemplo {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://HOST:PUERTO/BASE";

        String nombre = "USUARIO";

        String clave = "CONTRASEÑA";

        return DriverManager.getConnection(url, nombre, clave);
    }
}
