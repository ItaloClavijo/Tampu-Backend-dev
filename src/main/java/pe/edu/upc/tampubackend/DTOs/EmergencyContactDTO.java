package pe.edu.upc.tampubackend.DTOs;

public class EmergencyContactDTO {


    private String nombre;
    private String telefono;
    private String relacion;
    private String apiKey;
    private Long userId;  // para identificar al usuario relacionado (opcional pero útil)

    public EmergencyContactDTO() {}

    public EmergencyContactDTO(String nombre, String telefono, String relacion, String apiKey, Long userId) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.relacion = relacion;
        this.apiKey = apiKey;
        this.userId = userId;
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
