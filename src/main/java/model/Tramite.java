package model;

public class Tramite {

    private int idTramite;
    private int idSolicitante;
    private String tipoLicencia;
    private String estado;

    // Requisitos
    private Boolean certificadoMedico;
    private Boolean pagoOk;
    private Boolean multasOk;
    private String observaciones;

    // Exámenes
    private Double notaTeorica;
    private Double notaPractica;

    // Auditoría
    private int createdBy;
    private String createdAt; // TIMESTAMP como String


    // Constructor para CARGAR trámite (desde BD)
    public Tramite(int idTramite,
                   int idSolicitante,
                   String tipoLicencia,
                   String estado,
                   Boolean certificadoMedico,
                   Boolean pagoOk,
                   Boolean multasOk,
                   String observaciones,
                   Double notaTeorica,
                   Double notaPractica,
                   int createdBy,
                   String createdAt) {

        this.idTramite = idTramite;
        this.idSolicitante = idSolicitante;
        this.tipoLicencia = tipoLicencia;
        this.estado = estado;
        this.certificadoMedico = certificadoMedico;
        this.pagoOk = pagoOk;
        this.multasOk = multasOk;
        this.observaciones = observaciones;
        this.notaTeorica = notaTeorica;
        this.notaPractica = notaPractica;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters
    public int getIdTramite() {
        return idTramite;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public String getEstado() {
        return estado;
    }

    public Boolean getCertificadoMedico() {
        return certificadoMedico;
    }

    public Boolean getPagoOk() {
        return pagoOk;
    }

    public Boolean getMultasOk() {
        return multasOk;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Double getNotaTeorica() {
        return notaTeorica;
    }

    public Double getNotaPractica() {
        return notaPractica;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
