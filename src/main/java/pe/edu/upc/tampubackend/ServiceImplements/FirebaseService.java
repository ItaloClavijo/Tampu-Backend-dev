package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import pe.edu.upc.tampubackend.Services.IUserService;

@Service
public class FirebaseService {

    @Autowired
    private IUserRepository userRepository;

    public void saveFirebaseToken(Long userId, String firebaseToken) {
        // Buscar el usuario por su ID
        Users user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Establecer el token de Firebase
        user.setFirebaseToken(firebaseToken);

        // Guardar el usuario con el token de Firebase actualizado
        userRepository.save(user);
    }

}
