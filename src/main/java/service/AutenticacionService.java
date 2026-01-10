package service;

import dao.UsuarioDao;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class AutenticacionService {

    public static Usuario login(String username, String passwordPlano) {

        //  Validar campos
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username obligatorio");
        }

        if (passwordPlano == null || passwordPlano.trim().isEmpty()) {
            throw new IllegalArgumentException("Contraseña obligatoria");
        }

        // Buscar usuario
        Usuario usuario = UsuarioDao.buscarPorUsername(username);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario no existe");
        }

        // Verificar estado
        if (!usuario.estaActivo()) {
            throw new IllegalArgumentException("Usuario inactivo");
        }

        //  Verificar contraseña
        if (!BCrypt.checkpw(passwordPlano, usuario.getPasswordHash())) {
            throw new IllegalArgumentException("Credenciales incorrectas");
        }

        //  Login exitoso
        return usuario;
    }
}
