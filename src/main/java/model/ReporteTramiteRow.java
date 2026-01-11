package model;

public class ReporteTramiteRow {

    private int idTramite;
    private String cedula;
    private String nombre;
    private String tipoLicencia;
    private String estado;
    private String fechaSolicitud; // desde created_at (solo fecha)
    private String fechaEmision;   // puede ser null/-

    public ReporteTramiteRow(int idTramite, String cedula, String nombre,
                             String tipoLicencia, String estado,
                             String fechaSolicitud, String fechaEmision) {
        this.idTramite = idTramite;
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoLicencia = tipoLicencia;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEmision = fechaEmision;
    }

    public int getIdTramite() { return idTramite; }
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getTipoLicencia() { return tipoLicencia; }
    public String getEstado() { return estado; }
    public String getFechaSolicitud() { return fechaSolicitud; }
    public String getFechaEmision() { return fechaEmision; }
}
