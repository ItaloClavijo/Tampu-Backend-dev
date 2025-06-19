package pe.edu.upc.tampubackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Services.BiometricDataService;
import pe.edu.upc.tampubackend.Services.PredictionService;
import pe.edu.upc.tampubackend.DTOs.ResultadoPredictDTO;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/biometric-data")
public class BiometricDataController {

    @Autowired
    private BiometricDataService biometricDataService;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsersService usersService; // Servicio para gestionar usuarios

    @GetMapping("/test")
    public String test() {
        return "✅ Está vivo";
    }

    @PostMapping("/predict")
    public ResultadoPredictDTO recibirDatos(@RequestBody BiometricDataDTO data) {
        // Verificar si el usuario existe y crearlo si no existe
        UsersDTO userDto = new UsersDTO();

// Aquí setea todos los campos necesarios según tu entidad UsersDTO

        Users user = usersService.saveOrUpdateUser(userDto);


        // Enviar los datos a FastAPI para obtener el resultado
        ResultadoPredictDTO resultado = predictionService.enviarAFastAPI(data);

        String apiResponseJson = null;
        try {
            // Convertir la respuesta del API en formato JSON
            apiResponseJson = objectMapper.writeValueAsString(resultado);
        } catch (JsonProcessingException e) {
            System.err.println("Error al procesar la respuesta JSON: " + e.getMessage());
            e.printStackTrace();
        }

        // Guardar los datos biométricos y la respuesta del API en la base de datos
        if (apiResponseJson != null) {
            biometricDataService.saveWithApiResponse(data, apiResponseJson);
        }

        // Si es ansiedad fuerte (nivel 2), se envía una alerta
        if (resultado.getNivel() == 2) {
            // Lógica para enviar una notificación de emergencia (SMS, WhatsApp, Push)
            System.out.println("🚨 Ansiedad fuerte detectada. Enviar alerta.");
            // Aquí iría la llamada a la función de notificación (SMS, WhatsApp, Push)
        }

        System.out.println("➡ Resultado desde FastAPI:");
        System.out.println(resultado);

        return resultado;
    }
}
