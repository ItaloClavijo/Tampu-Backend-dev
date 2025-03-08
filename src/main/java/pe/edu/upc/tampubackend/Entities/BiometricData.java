package pe.edu.upc.tampubackend.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class BiometricData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private double heartRate;
    private double bloodPressure;
    private double spo2;
    private double stressLevel;     // Nivel de estrés (escala del smartwatch)
    private double sleepScore;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public double getSpo2() {
        return spo2;
    }

    public void setSpo2(double spo2) {
        this.spo2 = spo2;
    }

    public double getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(double stressLevel) {
        this.stressLevel = stressLevel;
    }

    public double getSleepScore() {
        return sleepScore;
    }

    public void setSleepScore(double sleepScore) {
        this.sleepScore = sleepScore;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}




