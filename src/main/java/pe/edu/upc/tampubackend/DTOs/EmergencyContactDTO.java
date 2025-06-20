package pe.edu.upc.tampubackend.DTOs;

import pe.edu.upc.tampubackend.Entities.Users;


public class EmergencyContactDTO {
    private String nombre;
    private String telefono;
    private String email;
    private String relacion;

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public EmergencyContactDTO() {}

    public EmergencyContactDTO(String nombre, String telefono, String email, String relacion) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.relacion = relacion;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
