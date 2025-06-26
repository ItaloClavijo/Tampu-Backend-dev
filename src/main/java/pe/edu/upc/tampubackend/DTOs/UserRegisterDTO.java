package pe.edu.upc.tampubackend.DTOs;

public class UserRegisterDTO {

    private String username;
    private String password;
    private String email;
    private int edad;

    // Datos del contacto de emergencia
    private String contactoNombre;
    private String contactoTelefono;
    private String contactoRelacion;
    private String contactoApiKey;

    public UserRegisterDTO() {}

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

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public String getContactoRelacion() {
        return contactoRelacion;
    }

    public void setContactoRelacion(String contactoRelacion) {
        this.contactoRelacion = contactoRelacion;
    }

    public String getContactoApiKey() {
        return contactoApiKey;
    }

    public void setContactoApiKey(String contactoApiKey) {
        this.contactoApiKey = contactoApiKey;
    }
}
