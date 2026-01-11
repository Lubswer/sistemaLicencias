package model;

public class Solicitante {

    private int idSolicitante;
    private String cedula;
    private String nombre;
    private String fechaNacimiento;

    // Constructor para CREAR solicitante (sin id)
    public Solicitante(String cedula, String nombre, String fechaNacimiento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Constructor para CARGAR solicitante (con id)
    public Solicitante(int idSolicitante, String cedula, String nombre, String fechaNacimiento) {
        this.idSolicitante = idSolicitante;
        this.cedula = cedula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public String getCedulaSolicitante() {
        return cedula;
    }

    public String getNombreSolicitante() {
        return nombre;
    }

    public String getFechaNacimientoSolicitante() {
        return fechaNacimiento;
    }

}

