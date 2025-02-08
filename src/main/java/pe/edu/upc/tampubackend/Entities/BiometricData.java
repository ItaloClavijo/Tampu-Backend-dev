package pe.edu.upc.tampubackend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BiometricData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private double heartRate;
    private double bloodPressure;
    private double spo2;
    private double stressLevel;     // Nivel de estrés (escala del smartwatch)
    private double sleepScore;
    private LocalDateTime date;
}
