package pe.edu.upc.tampubackend.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BiometricData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del id
    private Long id;

    private Double ECG;
    private Double HRV;
    private Double MOVIMIENTO;
    private Integer SpO2;
    private LocalDateTime timestamp;

    @Lob
    private String apiResponse;

    @Version  // Coloca la anotación @Version para gestionar la concurrencia optimista
    private Long version;

    @ManyToOne  // Relación con la entidad Users
    @JoinColumn(name = "user_id")
    private Users user; // Relación con la entidad Users

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getECG() {
        return ECG;
    }

    public void setECG(Double ECG) {
        this.ECG = ECG;
    }

    public Double getHRV() {
        return HRV;
    }

    public void setHRV(Double HRV) {
        this.HRV = HRV;
    }

    public Double getMOVIMIENTO() {
        return MOVIMIENTO;
    }

    public void setMOVIMIENTO(Double MOVIMIENTO) {
        this.MOVIMIENTO = MOVIMIENTO;
    }

    public Integer getSpO2() {
        return SpO2;
    }

    public void setSpO2(Integer SpO2) {
        this.SpO2 = SpO2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(String apiResponse) {
        this.apiResponse = apiResponse;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
