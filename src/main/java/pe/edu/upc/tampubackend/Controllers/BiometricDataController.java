package pe.edu.upc.tampubackend.Controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.BiometricDataDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Notification;
import pe.edu.upc.tampubackend.Repositories.IBiometricDataRepository;
import pe.edu.upc.tampubackend.Repositories.IEmergencyContactRepository;
import pe.edu.upc.tampubackend.ServiceImplements.NotificationServiceImplement;
import pe.edu.upc.tampubackend.ServiceImplements.WhatsAppUtil;
import pe.edu.upc.tampubackend.Services.BiometricDataService;
import pe.edu.upc.tampubackend.Services.PredictionService;
import pe.edu.upc.tampubackend.DTOs.ResultadoPredictDTO;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Services.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private IUserService usersService; // Servicio para gestionar usuarios

    @Autowired
    private IEmergencyContactRepository emergencyContactRepository;

    @Autowired
    private NotificationServiceImplement notificationService;

    @GetMapping("/test")
    public String test() {
        return "✅ Está vivo";
    }

    /*@PostMapping("/predict")
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
    }*/

    @PostMapping("/predict")
    public ResultadoPredictDTO enviarAFastAPI(@org.springframework.web.bind.annotation.RequestBody BiometricDataDTO data) {

        System.out.println("🎯 Objeto recibido desde Android:");
        System.out.println("ECG = " + data.getECG());
        System.out.println("HRV = " + data.getHRV());
        System.out.println("MOVIMIENTO = " + data.getMOVIMIENTO());
        System.out.println("SpO2 = " + data.getSpO2());
        System.out.println("user_id = " + data.getUser_id());

        OkHttpClient client = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        // ✅ Crear JSON solo con los campos que FastAPI espera
        ObjectNode json = objectMapper.createObjectNode();
        json.put("user_id", data.getUser_id());
        json.put("ECG", data.getECG());
        json.put("HRV", data.getHRV());
        json.put("MOVIMIENTO", data.getMOVIMIENTO());
        json.put("SpO2", data.getSpO2());

        System.out.println("➡ JSON que se enviará a FastAPI:");
        System.out.println(json.toString());

        // ✅ Usar MediaType y RequestBody correctamente
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json.toString(), mediaType);

        Request request = new Request.Builder()
                .url("https://tampu-api.onrender.com/predict")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return new ResultadoPredictDTO(-1, "Error al conectar con FastAPI: " + response.code() + " " + response.message());
            }

            String jsonResponse = response.body().string();
            System.out.println("🔍 Respuesta del Modelo de Machine Learning (FastAPI): " + jsonResponse);
            ResultadoPredictDTO resultado = objectMapper.readValue(jsonResponse, ResultadoPredictDTO.class);


            // ✅ Si es ansiedad fuerte (nivel 2), enviar mensaje por WhatsApp
            if (resultado.getNivel() == 2) {
                System.out.println("🚨 Ansiedad fuerte detectada. Enviando alerta por WhatsApp...");

                Users usuario = usersService.findById(data.getUser_id());
                List<EmergencyContact> contactos = emergencyContactRepository.findByUserId(usuario.getId());
                EmergencyContact contacto = contactos.isEmpty() ? null : contactos.get(contactos.size() - 1);

                if (contacto != null) {
                    String mensaje = "⚠️ Alerta: Se ha detectado un episodio de ansiedad fuerte en el usuario "
                            + usuario.getUsername() + ".\n"
                            + "Contacto de apoyo: " + contacto.getNombre() + " (" + contacto.getRelacion() + ")";

                    // convertimos el Long a String (como lo necesita CallMeBot)
                    String apiKey = String.valueOf(contacto.getApiKey());
                    String telefono = contacto.getTelefono();
                    // ahora llamas así
                    WhatsAppUtil.enviarAlerta(telefono, mensaje, apiKey);
                } else {
                    System.out.println("❗ Usuario sin contacto de emergencia registrado.");
                }

//                ✅ Enviar notificación push al usuario
//                if (usuario.getFirebaseToken() != null) {
//                    Notification notification = new Notification();
//                    notification.setRecipientToken(usuario.getFirebaseToken());
//                    notification.setTitle("🚨 Ansiedad detectada");
//                    notification.setBody("Hemos detectado un episodio de ansiedad fuerte. Respira profundamente.");
//                    notification.setImage("https://i.imgur.com/OdL0XPt.png"); // Imagen opcional
//                    notification.setDate(LocalDateTime.now());
//                    notification.setUser(usuario);
//                    notification.setEmergencyContact(contacto); // opcional
//
//                    Map<String, String> extraData = new HashMap<>();
//                    extraData.put("nivel", String.valueOf(resultado.getNivel()));
//                    extraData.put("user_id", String.valueOf(usuario.getId()));
//                    notification.setData(extraData);
//
//                    notificationService.sendNotiByToken(notification);
//                } else {
//                    System.out.println("❗ El usuario no tiene token de Firebase registrado.");
//                }

            }
            biometricDataService.saveWithApiResponse(data, jsonResponse);
            return objectMapper.readValue(jsonResponse, ResultadoPredictDTO.class);
        } catch (Exception e) {
            return new ResultadoPredictDTO(-1, "Excepción en FastAPI: " + e.getMessage());
        }
    }

}
