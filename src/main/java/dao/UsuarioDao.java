package dao;
import java.sql.*;
import model.Usuario;
public class UsuarioDao {
    public static Usuario buscarPorUsername(String username) {

        String sql = "SELECT * FROM usuario WHERE username = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

        return null; // usuario no existe
    }
}
