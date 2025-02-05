package pe.edu.upc.tampubackend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmergencyContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String email;
    private String tokenFCM;  // Para enviar notificaciones al contacto

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
