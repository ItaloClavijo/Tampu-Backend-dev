package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.Users;


public interface UsersService {
    // Método para guardar o actualizar el usuario
    Users saveOrUpdateUser(UsersDTO usersDTO);
}
