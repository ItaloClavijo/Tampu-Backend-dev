package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;

public interface BiometricDataService {
    // Métodos abstractos que implementará la clase BiometricDataServiceImplement
    public void saveWithApiResponse(BiometricDataDTO data, String apiResponse);
}
