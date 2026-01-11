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
    public static Tramite obterTramite(int idSolicitante) {

        String sql = "SELECT * FROM tramite WHERE id_solicitante = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSolicitante);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Tramite(
                        rs.getInt("id_tramite"),
                        rs.getInt("id_solicitante"),
                        rs.getString("tipo_licencia"),
                        rs.getString("estado"),
                        (Boolean) rs.getObject("certificado_medico"),
                        (Boolean) rs.getObject("pago_ok"),
                        (Boolean) rs.getObject("multas_ok"),
                        rs.getString("observaciones"),
                        rs.getDouble("nota_teorica"),
                        rs.getDouble("nota_practica"),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // no existe trámite para ese solicitante
    }
























































































































































































































































































































    public static void requisitos(int idTramite,boolean CMedico,boolean PRealizado,boolean SMultas,String observaciones,String nuevoEstado) {
        String sql = " UPDATE tramite SET certificado_medico = ?, pago_ok = ?, multas_ok = ?, observaciones = ?,estado = ? WHERE id_tramite = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, CMedico);
            ps.setBoolean(2, PRealizado);
            ps.setBoolean(3, SMultas);
            ps.setString(4, observaciones);
            ps.setString(5, nuevoEstado);
            ps.setInt(6, idTramite);

            ps.executeUpdate();

        } catch (SQLException err) {
            err.printStackTrace();
        }
    }
    public static void actualizarExamenes(int idTramite,double teoria,double practica, String estado){
        String sql="UPDATE tramite SET nota_teorica =?,nota_practica=?,estado=? WHERE id_tramite=?";
        try(Connection conn=ConexionDB.getConnection();
            PreparedStatement ps= conn.prepareStatement(sql)){
            ps.setDouble(1, teoria);
            ps.setDouble(2, practica);
            ps.setString(3, estado);
            ps.setInt(4, idTramite);
            ps.executeUpdate();
        }catch(SQLException milk){
            milk.printStackTrace();
        }
    }


    public static Tramite obtenerTramiteEnExamenes(int idSolicitante) {
        String sql = "SELECT * FROM tramite WHERE id_solicitante = ? AND estado = 'EN_EXAMENES'";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSolicitante);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Tramite(
                        rs.getInt("id_tramite"),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
