package service;
import dao.*;
import model.ReporteTramiteRow;
import model.Solicitante;
import model.Tramite;
import model.Usuario;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class AdminService {
    // / //////////////////////////////////////////////////////////////////////////////////
    public static List<ReporteTramiteRow> buscarTramitesReporte(
            String fechaInicio,
            String fechaFin,
            String estado,
            String tipoLicencia,
            String cedula
    ) {

        // Normalizar vacíos a null
        if (fechaInicio != null && fechaInicio.isBlank()) fechaInicio = null;
        if (fechaFin != null && fechaFin.isBlank()) fechaFin = null;

        if (estado == null || estado.equalsIgnoreCase("TODOS")) estado = null;
        if (tipoLicencia == null || tipoLicencia.equalsIgnoreCase("TODOS")) tipoLicencia = null;

        if (cedula != null) {
            cedula = cedula.trim();
            if (cedula.isBlank()) cedula = null;
        }

        // Validar fechas solo si existen
        if (fechaInicio != null) validarFecha(fechaInicio, "fecha inicio");
        if (fechaFin != null) validarFecha(fechaFin, "fecha fin");

        if (fechaInicio != null && fechaFin != null) {
            LocalDate ini = LocalDate.parse(fechaInicio);
            LocalDate fin = LocalDate.parse(fechaFin);
            if (fin.isBefore(ini)) {
                throw new IllegalArgumentException("La fecha fin no puede ser menor a la fecha inicio");
            }
        }

        // validar cédula  10 dígitos
        if (cedula != null && !cedula.matches("\\d{10}")) {
            throw new IllegalArgumentException("La cédula debe tener 10 dígitos");
        }

        return ReporteDao.buscarTramites(fechaInicio, fechaFin, estado, tipoLicencia, cedula);
    }

    private static void validarFecha(String fecha, String campo) {
        try { LocalDate.parse(fecha); }
        catch (Exception e) {
            throw new IllegalArgumentException("Formato inválido en " + campo + " (yyyy-MM-dd)");
        }
    }
}
// / //////////////////////////////////////////////////////////////////////////////////