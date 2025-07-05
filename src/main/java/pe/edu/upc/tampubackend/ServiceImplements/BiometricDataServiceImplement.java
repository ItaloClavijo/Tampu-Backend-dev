package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.tampubackend.DTOs.BiometricDataHistorialDTO;
import pe.edu.upc.tampubackend.Entities.BiometricData;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IBiometricDataRepository;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.Services.BiometricDataService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Override
    @Transactional
    public List<BiometricDataHistorialDTO> getBiometricDataNivel2(Long userId) {
        // Obtener todos los registros de BiometricData con nivel 2 para el usuario
        List<BiometricData> resultList = biometricDataRepository.findByNivelAndUserIdOrderByTimestampDesc(2, userId);

        // Convertir los registros de BiometricData a BiometricDataResponseDTO
        List<BiometricDataHistorialDTO> hitorial = resultList.stream().map(biometricData -> {
            BiometricDataHistorialDTO dto = new BiometricDataHistorialDTO();
            dto.setMovimiento(biometricData.getMOVIMIENTO());
            dto.setEcg(biometricData.getECG());
            dto.setHrv(biometricData.getHRV());
            dto.setSpO2(biometricData.getSpO2());
            dto.setNivel(biometricData.getNivel());
            dto.setTimestamp(biometricData.getTimestamp());
            return dto;
        }).collect(Collectors.toList());

        return hitorial; // Devuelve los registros mapeados
    }


    @Override
    @Transactional
    public Integer getLastNivel(Long userId) {
        // Obtener el último registro para el userId
        Optional<BiometricData> lastRecord = biometricDataRepository.findTopByUserIdOrderByTimestampDesc(userId);

        // Si hay un registro, lo retornamos
        if (lastRecord.isPresent()) {
            return lastRecord.get().getNivel();
        }

        // Si no hay registros, retornamos null o -1 (dependiendo de lo que prefieras)
        return -1; // o null si prefieres indicar que no se encontró un nivel
    }

}

