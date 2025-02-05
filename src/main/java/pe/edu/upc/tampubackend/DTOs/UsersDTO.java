package pe.edu.upc.tampubackend.DTOs;

import lombok.Data;
import pe.edu.upc.tampubackend.Entities.Role;

import java.util.List;

@Data
public class UsersDTO {
    private String username;
    private String password;
    private Boolean enabled;
    private String email;
    private String phoneNumber;
    private List<Role> roles;
}
