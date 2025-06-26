package pe.edu.upc.tampubackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String relacion;
    @Column(name = "api_key")
    private String apiKey;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    public EmergencyContact() {
    }

    public EmergencyContact(Long id, String nombre, String telefono, String relacion, String apiKey, Users user) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.relacion = relacion;
        this.apiKey = apiKey;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
