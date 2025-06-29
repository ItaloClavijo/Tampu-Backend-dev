package pe.edu.upc.tampubackend.Controllers;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.UserDTO;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
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
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserRegisterDTO dto) {
        try {
            usersService.update(id, dto);
            return ResponseEntity.ok("✅ Usuario actualizado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al actualizar usuario: " + e.getMessage());
        }
    }


}
