package pe.edu.upc.tampubackend.DTOs;

import com.google.firebase.database.DatabaseError;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BiometricDataHistorialDTO {
    private Double movimiento;
    private Double ecg;
    private Double hrv;
    private Integer spO2;
    private Integer nivel;
    private LocalDateTime timestamp;

    // Getters y Setters
    public Double getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Double movimiento) {
        this.movimiento = movimiento;
    }

    public Double getEcg() {
        return ecg;
    }

    public void setEcg(Double ecg) {
        this.ecg = ecg;
    }

    public Double getHrv() {
        return hrv;
    }

    public void setHrv(Double hrv) {
        this.hrv = hrv;
    }

    public Integer getSpO2() {
        return spO2;
    }

    public void setSpO2(Integer spO2) {
        this.spO2 = spO2;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
