package pe.edu.upc.tampubackend.DTOs;
import lombok.Data;
import pe.edu.upc.tampubackend.Entities.Users;

@Data
public class EmergencyContactDTO {
    private String nombre;
    private String telefono;
    private String email;

    public EmergencyContactDTO() {}

    public EmergencyContactDTO(String nombre, String telefono, String email) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;

    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
