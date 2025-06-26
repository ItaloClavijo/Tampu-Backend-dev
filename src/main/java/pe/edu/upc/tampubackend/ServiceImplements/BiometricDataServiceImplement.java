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

import java.time.LocalDateTime;

@Service
public class BiometricDataServiceImplement implements BiometricDataService {

    @Autowired
    private IBiometricDataRepository biometricDataRepository;

    @Autowired
    private IUserRepository usersRepository;

    @Override
    @Transactional
    public void saveWithApiResponse(BiometricDataDTO data, String apiResponse) {
        // Validar que el usuario exista, si no lanzar excepción
        Users user = usersRepository.findById(data.getUser_id())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + data.getUser_id()));

        BiometricData biometricData = new BiometricData();
        biometricData.setECG(data.getECG());
        biometricData.setHRV(data.getHRV());
        biometricData.setMOVIMIENTO(data.getMOVIMIENTO());
        biometricData.setSpO2(data.getSpO2());
        biometricData.setTimestamp(
                data.getTimestamp() != null ? data.getTimestamp() : LocalDateTime.now()
        );
        biometricData.setApiResponse(apiResponse);
        biometricData.setUser(user);

        biometricDataRepository.save(biometricData);
    }
}
