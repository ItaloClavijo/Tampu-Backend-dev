package pe.edu.upc.tampubackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
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
}
