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
@RequestMapping("/api/users") // opcional pero recomendado
public class UsersController {

    @Autowired
    private IUserService usersService;

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
            return ResponseEntity.ok("✅ Usuario actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar usuario: " + e.getMessage());
        }
    }

    // Actualizar contacto por ID de user
    @PutMapping("/updatec/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody EmergencyContactDTO dto) {
        try {
            usersService.updateConctact(id, dto);
            return ResponseEntity.ok("✅ Usuario actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar usuario: " + e.getMessage());
        }
    }

    //
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id) {
        Users user = usersService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<EmergencyContact> getEmergencyContact(@PathVariable("id") Long id) {
        EmergencyContact contact = usersService.find(id); // Llamada al servicio

        if (contact != null) {
            return ResponseEntity.ok(contact); // Retorna el contacto si existe
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe el contacto
        }
    }
}
