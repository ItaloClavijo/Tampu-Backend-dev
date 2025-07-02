package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.BiometricDataHistorialDTO;
import pe.edu.upc.tampubackend.Entities.BiometricData;

import java.util.List;

public interface BiometricDataService {
    // Métodos abstractos que implementará la clase BiometricDataServiceImplement
    public void saveWithApiResponse(BiometricDataDTO data, String apiResponse);

    public List<BiometricDataHistorialDTO> getBiometricDataNivel2(Long userId);

    public Integer getLastNivel(Long userId);
}
