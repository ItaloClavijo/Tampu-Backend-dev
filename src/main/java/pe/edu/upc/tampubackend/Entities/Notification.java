package pe.edu.upc.tampubackend.Entities;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private LocalDateTime date;

    // Añadimos la colección de pares clave-valor
    @ElementCollection
    @CollectionTable(
            name = "notification_data",               // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "notification_id")
    )
    @MapKeyColumn(name = "data_key")    // Columna para la "clave" del Map
    @Column(name = "data_value")        // Columna para el "valor" del Map
    private Map<String, String> data = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = true)
    private EmergencyContact emergencyContact;

    public Notification() {
    }

    public Notification(Long id, String recipientToken, String title, String body, String image, LocalDateTime date, Map<String, String> data, Users user, EmergencyContact emergencyContact) {
        this.id = id;
        this.recipientToken = recipientToken;
        this.title = title;
        this.body = body;
        this.image = image;
        this.date = date;
        this.data = data;
        this.user = user;
        this.emergencyContact = emergencyContact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientToken() {
        return recipientToken;
    }

    public void setRecipientToken(String recipientToken) {
        this.recipientToken = recipientToken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }


}
