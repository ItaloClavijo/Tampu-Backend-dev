package pe.edu.upc.tampubackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.ResultadoPredictDTO;
import pe.edu.upc.tampubackend.Services.PredictionService;

@RestController
@RequestMapping("/api/ml")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @PostMapping("/predict")
    public ResultadoPredictDTO predict(@RequestBody BiometricDataDTO input) {
        return predictionService.enviarAFastAPI(input);
    }

    @GetMapping("/predict")
    public String test() {
        return "✅ Endpoint de predicción activo.";
    }
}
