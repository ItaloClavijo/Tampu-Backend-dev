package pe.edu.upc.tampubackend.ServiceImplements;

import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Services.UsersService;

@Service
public class UsersServiceImplement implements UsersService {

    @Autowired
    private IUserRepository userRepository;

    // Método para crear o actualizar el usuario a partir de BiometricDataDTO

    @Override
    public Users saveOrUpdateUser(UsersDTO data) {
        Users user = userRepository.findByUsername(data.getUsername()); // Cambia esto si es necesario
        if (user == null) {
            // Si el usuario no existe, creamos uno nuevo
            user = new Users();
            user.setUsername(data.getUsername()); // Usa el username del DTO
            user.setEmail(data.getEmail()); // Usa el email del DTO
            user.setEnabled(true); // O cualquier valor que necesites
            user.setPhoneNumber(data.getPhoneNumber()); // Usa el teléfono del DTO
        }

        // Puedes actualizar otros campos si es necesario
        return userRepository.save(user);
    }

}
