package pe.edu.upc.tampubackend.DTOs;
import lombok.Data;
import pe.edu.upc.tampubackend.Entities.Users;

@Data
public class EmergencyContactDTO {
    private String nombre;
    private String telefono;
    private String email;
}
