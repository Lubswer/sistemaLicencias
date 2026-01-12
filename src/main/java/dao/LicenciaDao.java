package dao;

import model.*;

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
            ps.setString(2, numeroLicencia );
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
    public static LicenciaModel obtenerLicencia(int idTramite) {

        String sql = """
        SELECT *
        FROM licencia
        WHERE id_tramite = ?
    """;

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idTramite);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new LicenciaModel(
                        rs.getInt("id_licencia"),
                        rs.getInt("id_tramite"),
                        rs.getString("numero_licencia"),
                        rs.getDate("fecha_emision").toString(),
                        rs.getDate("fecha_vencimiento").toString(),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la licencia", e);
        }

        return null; // el trámite aún no tiene licencia
    }
}
