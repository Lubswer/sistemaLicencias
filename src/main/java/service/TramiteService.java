package service;
import dao.*;
import dao.UsuarioDao;
import model.Solicitante;
import model.Usuario;

import java.time.LocalDate;
import java.time.Period;

public class TramiteService {
    public static void solicitante(String cedula, String nombre, String fechaN, String licencia, Usuario usuario){
        if (cedula == null || cedula.isBlank())
            throw new IllegalArgumentException("La cédula es obligatoria");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio");

        if (fechaN == null || fechaN.isBlank())
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");

        if (licencia == null || licencia.isBlank())
            throw new IllegalArgumentException("El tipo de licencia es obligatorio");

        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaN); // formato yyyy-MM-dd
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha inválido");
        }

        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 18) {
            throw new IllegalArgumentException("El solicitante debe ser mayor de 18 años");
        }
        TramiteDao.ingresarSolicitante(cedula,nombre,fechaN);
        int id_usuario = usuario.getIdUsuario();
        Solicitante solicitante1 = TramiteDao.obtenerSolicitante(cedula);
        int id_solicitante = solicitante1.getIdSolicitante();

        TramiteDao.insertarTramite(id_solicitante, licencia, id_usuario);



    }
}
