package service;
import dao.UsuarioDao;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UsuarioService {

    // LISTAR
    public static List<Usuario> listarUsuarios() {
        return UsuarioDao.listarUsuarios();
    }

    // BUSCAR
    public static Usuario buscarPorCedula(String cedula) {

        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cédula es obligatoria");
        }

        return UsuarioDao.buscarPorCedula(cedula);
    }

    // CREAR
    public static void crearUsuario(
            String cedula,
            String nombre,
            String username,
            String passwordPlano,
            String rol,
            String estado
    ) {

        if (cedula.isBlank() || nombre.isBlank() ||
                username.isBlank() || passwordPlano.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        if (UsuarioDao.buscarPorCedula(cedula) != null) {
            throw new IllegalArgumentException("La cédula ya está registrada");
        }

        if (UsuarioDao.buscarPorUsername(username) != null) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        String hash = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());

        Usuario u = new Usuario(
                nombre,
                cedula,
                username,
                hash,
                rol,
                estado
        );

        UsuarioDao.insertarUsuario(u);
    }

    public static void actualizarUsuario(
            String cedula,
            String nombre,
            String username,
            String passwordPlano, // opcional
            String rol,
            String estado
    ) {

        Usuario existente = UsuarioDao.buscarPorCedula(cedula);

        if (existente == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }

        // Si cambia username, validar que no esté ocupado
        if (!existente.getUsername().equals(username)) {
            if (UsuarioDao.buscarPorUsername(username) != null) {
                throw new IllegalArgumentException("El username ya está en uso");
            }
        }

        String passwordHash = null;
        if (passwordPlano != null && !passwordPlano.isBlank()) {
            passwordHash = BCrypt.hashpw(passwordPlano, BCrypt.gensalt());
        }

        UsuarioDao.actualizarUsuario(
                cedula,
                nombre,
                username,
                passwordHash,
                rol,
                estado
        );
    }
}
