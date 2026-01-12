package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
public class UsuarioDao {
    public static Usuario buscarPorUsername(String username) {

        String sql = "SELECT * FROM usuario WHERE username = ?";

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("rol"),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Usuario buscarPorCedula(String cedula) {

        String sql = "SELECT * FROM usuario WHERE cedula = ?";

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("rol"),
                        rs.getString("estado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static List<Usuario> listarUsuarios() {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY id_usuario";

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("cedula"),
                        rs.getString("username"),
                        rs.getString("password_hash"),
                        rs.getString("rol"),
                        rs.getString("estado")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public static void insertarUsuario(Usuario u) {

        String sql = """
        INSERT INTO usuario
        (nombre, cedula, username, password_hash, rol, estado)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCedula());
            ps.setString(3, u.getUsername());
            ps.setString(4, u.getPasswordHash());
            ps.setString(5, u.getRol());
            ps.setString(6, u.getEstado());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }
    public static void actualizarUsuario(
            String cedula,
            String nombre,
            String username,
            String passwordHash, // puede ser null
            String rol,
            String estado
    ) {

        String sql;

        if (passwordHash == null) {
            sql = """
            UPDATE usuario
            SET nombre = ?, username = ?, rol = ?, estado = ?
            WHERE cedula = ?
        """;
        } else {
            sql = """
            UPDATE usuario
            SET nombre = ?, username = ?, password_hash = ?, rol = ?, estado = ?
            WHERE cedula = ?
        """;
        }

        try (
                Connection conn = ConexionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, nombre);
            ps.setString(2, username);

            if (passwordHash == null) {
                ps.setString(3, rol);
                ps.setString(4, estado);
                ps.setString(5, cedula);
            } else {
                ps.setString(3, passwordHash);
                ps.setString(4, rol);
                ps.setString(5, estado);
                ps.setString(6, cedula);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario", e);
        }
    }





}
