package dao;

import model.ReporteTramiteRow;
import model.Tramite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ReporteDao {
    // / //////////////////////////////////////////////////////////////////////////////////
    public static List<ReporteTramiteRow> buscarTramites(
            String fechaInicio,
            String fechaFin,
            String estado,
            String tipoLicencia,
            String cedula
    ) {

        List<ReporteTramiteRow> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT
                t.id_tramite,
                s.cedula,
                s.nombre,
                t.tipo_licencia,
                t.estado,
                DATE(t.created_at) AS fecha_solicitud,
                l.fecha_emision
            FROM tramite t
            JOIN solicitante s ON s.id_solicitante = t.id_solicitante
            LEFT JOIN licencia l ON l.id_tramite = t.id_tramite
            WHERE 1=1
        """);

        if (fechaInicio != null) sql.append(" AND DATE(t.created_at) >= ?");
        if (fechaFin != null) sql.append(" AND DATE(t.created_at) <= ?");
        if (estado != null) sql.append(" AND t.estado = ?");
        if (tipoLicencia != null) sql.append(" AND t.tipo_licencia = ?");
        if (cedula != null) sql.append(" AND s.cedula = ?");

        sql.append(" ORDER BY t.id_tramite DESC");

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int i = 1;

            if (fechaInicio != null) ps.setString(i++, fechaInicio);
            if (fechaFin != null) ps.setString(i++, fechaFin);
            if (estado != null) ps.setString(i++, estado);
            if (tipoLicencia != null) ps.setString(i++, tipoLicencia);
            if (cedula != null) ps.setString(i++, cedula);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String fechaSolicitud = rs.getDate("fecha_solicitud").toString();

                Date fe = rs.getDate("fecha_emision");
                String fechaEmision = (fe != null) ? fe.toString() : "-";

                lista.add(new ReporteTramiteRow(
                        rs.getInt("id_tramite"),
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("tipo_licencia"),
                        rs.getString("estado"),
                        fechaSolicitud,
                        fechaEmision
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
// / //////////////////////////////////////////////////////////////////////////////////