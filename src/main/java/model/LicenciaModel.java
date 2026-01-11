package model;

public class LicenciaModel {

    private int idLicencia;
    private int idTramite;
    private String numeroLicencia;
    private String fechaEmision;      // yyyy-MM-dd
    private String fechaVencimiento;  // yyyy-MM-dd
    private int createdBy;
    private String createdAt;         // TIMESTAMP como String

    // Constructor para CREAR licencia (sin id, sin createdAt)
    public LicenciaModel(int idTramite,
                    String numeroLicencia,
                    String fechaEmision,
                    String fechaVencimiento,
                    int createdBy) {

        this.idTramite = idTramite;
        this.numeroLicencia = numeroLicencia;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.createdBy = createdBy;
    }

    // Constructor para CARGAR licencia (desde BD)
    public LicenciaModel(int idLicencia,
                    int idTramite,
                    String numeroLicencia,
                    String fechaEmision,
                    String fechaVencimiento,
                    int createdBy,
                    String createdAt) {

        this.idLicencia = idLicencia;
        this.idTramite = idTramite;
        this.numeroLicencia = numeroLicencia;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters
    public int getIdLicencia() {
        return idLicencia;
    }

    public int getIdTramite() {
        return idTramite;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
