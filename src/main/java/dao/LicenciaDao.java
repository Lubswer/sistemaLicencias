package dao;

import model.Tramite;

import java.sql.*;

public class LicenciaDao {
    public static void generarLicencia(int idTramite,String numeroLicencia,String fechaEmision,String fechaVencimiento,int usuarioLogueadoID){

        String sql = """
            INSERT INTO licencia
            (id_tramite, numero_licencia, fecha_emision, fecha_vencimiento, created_by)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idTramite);
            ps.setString(2, numeroLicencia);
            ps.setString(3, fechaEmision);
            ps.setString(4, fechaVencimiento);
            ps.setInt(5, usuarioLogueadoID);

            ps.executeUpdate(); // le insertaa

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar la licencia", e);
        }
    }
    public static void actualiarEstado(String estadoNuevo , int idTramite){
        String sql = "Update tramite set estado = ? where id_tramite = ?";
        try(Connection conn = ConexionDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1,estadoNuevo);
            ps.setInt(2,idTramite);
            ps.executeUpdate();
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
