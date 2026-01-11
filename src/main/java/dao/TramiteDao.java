package dao;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TramiteDao {
    public static void ingresarSolicitante(String cedulaP,String nombreP,String fechaNP){
        String sql = "insert into solicitante(cedula, nombre, fecha_nacimiento) values(?,?,?);";
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1,cedulaP);
            ps.setString(2,nombreP);
            ps.setString(3,fechaNP);
            ps.executeUpdate();


        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }
    public static Solicitante obtenerSolicitante(String cedulaS) {

        String sql = "SELECT * FROM solicitante WHERE cedula = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedulaS);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Solicitante(
                        rs.getInt("id_solicitante"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_nacimiento").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // solicitante no existe
    }

    public static void insertarTramite(int idSolicitante,String tipoLicencia,int idUsuario){
        String sql = """
        INSERT INTO tramite (id_solicitante,tipo_licencia,estado,created_by)
        VALUES (?, ?, ?, ?);
        """;
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setInt(1, idSolicitante);
            ps.setString(2, tipoLicencia);
            ps.setString(3, "PENDIENTE");
            ps.setInt(4, idUsuario);

            ps.executeUpdate();

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }

    }
}
