package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.ResultadoPredictDTO;
import pe.edu.upc.tampubackend.Services.PredictionService;

import java.util.Map;
import java.util.HashMap;

@Service
public class PredictionServiceImplement implements PredictionService {

    private final String API_URL = "https://tampu-api.onrender.com/predict";

    @Override
    public ResultadoPredictDTO enviarAFastAPI(BiometricDataDTO data) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear payload manual sin timestamp y evitando valores null
        Map<String, Object> payload = new HashMap<>();
        if (data.getUser_id() != null) payload.put("user_id", data.getUser_id());
        if (data.getECG() != null) payload.put("ECG", data.getECG());
        if (data.getHRV() != null) payload.put("HRV", data.getHRV());
        if (data.getMOVIMIENTO() != null) payload.put("MOVIMIENTO", data.getMOVIMIENTO());
        if (data.getSpO2() != null) payload.put("SpO2", data.getSpO2());

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<ResultadoPredictDTO> response = restTemplate
                    .postForEntity(API_URL, request, ResultadoPredictDTO.class);
            return response.getBody();
        } catch (Exception e) {
            return new ResultadoPredictDTO(-1, "Error al conectar con FastAPI: " + e.getMessage());
        }
    }

    // Método auxiliar para convertir un Map a BiometricDataDTO (por si se usa desde otro lugar)
    private BiometricDataDTO convertirMapADataDTO(Map<String, Object> datos) {
        BiometricDataDTO dto = new BiometricDataDTO();
        dto.setUser_id((Long) datos.get("user_id"));
        dto.setECG((Double) datos.get("ECG"));
        dto.setHRV((Double) datos.get("HRV"));
        dto.setMOVIMIENTO((Double) datos.get("MOVIMIENTO"));
        dto.setSpO2((Integer) datos.get("SpO2"));
        return dto;
    }

    @Override
    public ResultadoPredictDTO getPrediction(Map<String, Object> input) {
        BiometricDataDTO dto = convertirMapADataDTO(input);
        return enviarAFastAPI(dto);
    }
}
