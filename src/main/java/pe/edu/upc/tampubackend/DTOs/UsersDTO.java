package pe.edu.upc.tampubackend.DTOs;

import jakarta.persistence.Column;
import pe.edu.upc.tampubackend.Entities.Role;
import java.util.List;

public class UsersDTO {

    private String username;
    private String password;
    private Boolean enabled;
    private String email;
    private Integer edad;
    private String sexo;
    private String carrera;

    public UsersDTO(String username, String password, Boolean enabled, String email, Integer edad, String sexo, String carrera) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.email = email;
        this.edad = edad;
        this.sexo = sexo;
        this.carrera = carrera;
    }

    public UsersDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
