package service;
import dao.*;
import model.Solicitante;
import model.Tramite;
import model.Usuario;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.Period;

public class TramiteService {
    public static void solicitante(String cedula, String nombre, String fechaN, String licencia, Usuario usuario){
        if (cedula == null || cedula.isBlank())
            throw new IllegalArgumentException("La cédula es obligatoria");
        int cedulaNum = convertirTexto(cedula);
        String cedulaVerificada = String.valueOf(cedulaNum);

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
        TramiteDao.ingresarSolicitante(cedulaVerificada,nombre,fechaN);
        int id_usuario = usuario.getIdUsuario();
        Solicitante solicitante1 = TramiteDao.obtenerSolicitante(cedula);
        int id_solicitante = solicitante1.getIdSolicitante();

        TramiteDao.insertarTramite(id_solicitante, licencia, id_usuario);
    }
    public static int convertirTexto(String texto) throws NumberFormatException {
        return Integer.parseInt(texto);
    }
    public static Solicitante buscarPorCedula(String cedula){
        if (cedula == null || cedula.isBlank()){
            throw new IllegalArgumentException("La cédula es obligatoria");
        }
        int cedulaNum = convertirTexto(cedula);
        String cedulaVerificada = String.valueOf(cedulaNum);
        Solicitante solicitante1 = TramiteDao.obtenerSolicitante(cedula);
        return solicitante1;

    }

    public static Tramite obtenerTramiteSolicitante(int idSolicitante){
        Tramite tramiteRescatado = TramiteDao.obterTramite( idSolicitante);
        return tramiteRescatado;

    }
    public static void generarLicencia(int idTramite,String numeroLicencia, String fechaEmision, String fechaVencimiento,int usuarioLogueadoID, String estadoTramite){
        if (!estadoTramite.equals("APROBADO")){
            throw  new IllegalArgumentException("El estado del tramite no ha sido aprobado");
        }
        //  Validar id del trámite
        if (idTramite <= 0) {
            throw new IllegalArgumentException("Trámite inválido");
        }

        //  Validar número de licencia
        if (numeroLicencia == null || numeroLicencia.isBlank()) {
            throw new IllegalArgumentException("El número de licencia es obligatorio");
        }

        //  Validar fecha de emisión
        if (fechaEmision == null || fechaEmision.isBlank()) {
            throw new IllegalArgumentException("La fecha de emisión es obligatoria");
        }

        //  Validar fecha de vencimiento
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
        }

        //  Validar formato de fechas (yyyy-MM-dd)
        LocalDate emision;
        LocalDate vencimiento;

        try {
            emision = LocalDate.parse(fechaEmision);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha de emisión inválido");
        }

        try {
            vencimiento = LocalDate.parse(fechaVencimiento);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de fecha de vencimiento inválido");
        }

        //  Validar coherencia de fechas
        if (!vencimiento.isAfter(emision)) {
            throw new IllegalArgumentException(
                    "La fecha de vencimiento debe ser posterior a la fecha de emisión"
            );
        }

        //  Validar usuario logueado
        if (usuarioLogueadoID <= 0) {
            throw new IllegalArgumentException("Usuario no válido para generar licencia");
        }

        LicenciaDao.generarLicencia(idTramite,numeroLicencia,fechaEmision,fechaVencimiento,usuarioLogueadoID);


    }
    public static void cambiarEstadoLicencia(String estado, int idTramite){
        LicenciaDao.actualiarEstado(estado, idTramite);
    }

}
