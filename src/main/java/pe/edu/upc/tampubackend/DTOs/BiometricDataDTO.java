package pe.edu.upc.tampubackend.DTOs;
import pe.edu.upc.tampubackend.Entities.Users;

import java.time.LocalDateTime;


public class BiometricDataDTO {
    private Users user;
    private double heartRate;
    private double bloodPressure;
    private double spo2;
    private LocalDateTime date;
    private double stressLevel;     // Nivel de estrés (escala del smartwatch)
    private double sleepScore;
}


