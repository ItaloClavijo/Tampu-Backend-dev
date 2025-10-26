package pe.edu.upc.tampubackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.EmergencyContactDTO;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Services.IUserService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private IUserService usersService;

    // Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO dto) {
        try {
            usersService.registerUser(dto);
            return ResponseEntity.ok("Usuario registrado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar usuario por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UsersDTO dto) {
        try {
            usersService.updateUser(id, dto);
            return ResponseEntity.ok("✅ Datos del usuario actualizados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar datos del usuario: " + e.getMessage());
        }
    }

    // Actualizar contacto de emergencia por ID de usuario
    @PutMapping("/updatec/{id}")
    public ResponseEntity<String> updateEmergencyContact(@PathVariable("id") Long id, @RequestBody EmergencyContactDTO dto) {
        try {
            usersService.updateConctact(id, dto);
            return ResponseEntity.ok("✅ Contacto de emergencia actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar contacto de emergencia: " + e.getMessage());
        }
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        Users user = usersService.findById(id);
        // El service devuelve new Users() si no existe; validamos por ID nulo
        if (user == null || user.getId() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Obtener contacto de emergencia por ID de usuario
    @GetMapping("/contact/{id}")
    public ResponseEntity<EmergencyContact> getEmergencyContact(@PathVariable("id") Long id) {
        EmergencyContact contact = usersService.find(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar perfil (usuario + dependencias)
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> eliminarPerfil(@PathVariable Long userId) {
        try {
            boolean eliminado = usersService.eliminarPerfil(userId);
            if (eliminado) {
                return ResponseEntity.ok("Perfil eliminado correctamente.");
            } else {
                // No existe o no se pudo eliminar
                return ResponseEntity.status(404).body("Usuario no encontrado o no se pudo eliminar.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el perfil.");
        }
    }
}
