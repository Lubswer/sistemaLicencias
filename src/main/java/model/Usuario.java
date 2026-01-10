package model;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String cedula;
    private String username;
    private String passwordHash;
    private String rol;       // "ADMIN" | "ANALISTA"
    private String estado;    // "ACTIVO" | "INACTIVO"


    public Usuario(){}

    // 1️⃣ Constructor para CREAR usuario (sin id)
    public Usuario(String nombre,
                   String cedula,
                   String username,
                   String passwordHash,
                   String rol,
                   String estado) {

        this.nombre = nombre;
        this.cedula = cedula;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estado = estado;
    }

    // Constructor para CARGAR usuario (con id)
    public Usuario(int idUsuario,
                   String nombre,
                   String cedula,
                   String username,
                   String passwordHash,
                   String rol,
                   String estado) {

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.cedula = cedula;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estado = estado;
    }

    // ===== Getters =====

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }

    // ===== Métodos útiles =====

    public boolean estaActivo() {
        return "ACTIVO".equals(this.estado);
    }

    public boolean esAdmin() {
        return "ADMIN".equals(this.rol);
    }

    public boolean esAnalista() {
        return "ANALISTA".equals(this.rol);
    }
}