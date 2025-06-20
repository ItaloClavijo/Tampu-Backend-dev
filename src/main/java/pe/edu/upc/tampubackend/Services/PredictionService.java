package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.ResultadoPredictDTO;
import java.util.Map;

public interface PredictionService {
    ResultadoPredictDTO enviarAFastAPI(BiometricDataDTO data);
    ResultadoPredictDTO getPrediction(Map<String, Object> input);
}
