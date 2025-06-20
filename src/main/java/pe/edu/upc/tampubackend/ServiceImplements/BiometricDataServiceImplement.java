package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.tampubackend.Entities.BiometricData;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IBiometricDataRepository;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.Services.BiometricDataService;

@Service
public class BiometricDataServiceImplement implements BiometricDataService {

    @Autowired
    private IBiometricDataRepository biometricDataRepository;

    @Autowired
    private IUserRepository usersRepository;

    @Override
    @Transactional
    public void saveWithApiResponse(BiometricDataDTO data, String apiResponse) {
        // Buscar el usuario por ID
        Users user = usersRepository.findById(data.getUser_id())
                .orElseGet(() -> {
                    // Si el usuario no existe, se crea un nuevo usuario
                    Users newUser = new Users();
                    newUser.setId(data.getUser_id());
                    newUser.setUsername("Nuevo Usuario"); // Puedes poner datos por defecto o pedirlos
                    newUser.setEmail("email@domain.com"); // Puedes poner un email por defecto o asignarlo de alguna otra forma

                    return usersRepository.save(newUser);  // Guardamos al nuevo usuario
                });

        // Crear la entidad BiometricData
        BiometricData biometricData = new BiometricData();
        biometricData.setECG(data.getECG());
        biometricData.setHRV(data.getHRV());
        biometricData.setMOVIMIENTO(data.getMOVIMIENTO());
        biometricData.setSpO2(data.getSpO2());
        biometricData.setTimestamp(data.getTimestamp());
        biometricData.setApiResponse(apiResponse);

        // Asociamos al usuario
        biometricData.setUser(user);

        // Guardamos la entidad BiometricData en la base de datos
        biometricDataRepository.save(biometricData);
    }
}
