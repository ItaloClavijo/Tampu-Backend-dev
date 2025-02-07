package pe.edu.upc.tampubackend.DTOs;

import lombok.Data;
import pe.edu.upc.tampubackend.Entities.BiometricData;
import pe.edu.upc.tampubackend.Entities.Users;

import java.time.LocalDateTime;

@Data
public class BiometricDataDTO {
    private Users user;
    private double heartRate;
    private double bloodPressure;
    private double spo2;
    private LocalDateTime date;
    private double stresslevel;

    public BiometricDataDTO() {}

    public BiometricDataDTO( double heartRate, double bloodPressure, double spo2, LocalDateTime date, double stresslevel ) {

        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.spo2 = spo2;
        this.date = date;
        this.stresslevel = stresslevel;

    }

    public double getHeartRate() { return heartRate; }
    public void setHeartRate( double heartRate ) { this.heartRate = heartRate; }

    public double getBloodPressure() { return bloodPressure; }
    public void setBloodPressure ( double bloodPressure ) { this.bloodPressure = bloodPressure; }

    public double getSpo2() { return spo2; }
    public void setSpo2 ( double spo2 ) { this.spo2 = spo2; }

    public LocalDateTime getDate() { return date; }
    public void setDate ( LocalDateTime date ) { this.date = date; }

    public double getStresslevel() { return stresslevel; }
    public void setStresslevel ( double stresslevel ) { this.stresslevel = stresslevel; }


}


