package pe.edu.upc.tampubackend.DTOs;

public class  UserDTO {

    private String username;
    private String password;
    private String email;
    private int edad;
    private String sexo;
    private String carrera;

    public UserDTO(String username, String password, String email, int edad, String sexo, String carrera) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.edad = edad;
        this.sexo = sexo;
        this.carrera = carrera;
    }

    public UserDTO() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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
