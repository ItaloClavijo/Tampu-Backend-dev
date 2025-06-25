package pe.edu.upc.tampubackend.DTOs;

public class EmergencyContactDTO {

    private Long id;
    private String nombre;
    private String telefono;
    private String relacion;
    private Long userId;  // para identificar al usuario relacionado (opcional pero útil)

    public EmergencyContactDTO() {}

    public EmergencyContactDTO(Long id, String nombre, String telefono, String relacion, Long userId) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.relacion = relacion;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
