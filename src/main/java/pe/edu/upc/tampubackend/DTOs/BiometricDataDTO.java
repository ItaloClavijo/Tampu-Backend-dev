package pe.edu.upc.tampubackend.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class BiometricDataDTO {

    @JsonProperty("user_id")
    private Long user_id;

    @JsonProperty("ECG")
    private Double ECG;

    @JsonProperty("HRV")
    private Double HRV;

    @JsonProperty("MOVIMIENTO")
    private Double MOVIMIENTO;

    @JsonProperty("SpO2")
    private Integer SpO2;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    private String apiResponse;

    private Integer nivel;

    // Getters y Setters
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public void setSpO2(Integer spO2) {
        SpO2 = spO2;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
