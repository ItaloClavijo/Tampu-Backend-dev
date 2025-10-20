package pe.edu.upc.tampubackend.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tampubackend.Entities.ApiError;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Security.JwtRequest;
import pe.edu.upc.tampubackend.Security.JwtResponse;
import pe.edu.upc.tampubackend.Security.JwtTokenUtil;
import pe.edu.upc.tampubackend.ServiceImplements.FirebaseService;
import pe.edu.upc.tampubackend.ServiceImplements.JwtUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private JwtUserDetailsService userDetailsService;
    @Autowired private FirebaseService firebaseService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest req) {
        try {
            // 1) Verifica que el usuario exista (CP07)
            Users user;
            try {
                user = userDetailsService.getUserByUsername(req.getUsername());
            } catch (UsernameNotFoundException e) {
                return ResponseEntity.status(404).body(new ApiError("Usuario no registrado"));
            }

            // 2) Autentica la contraseña (CP08)
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
                );
            } catch (BadCredentialsException e) {
                return ResponseEntity.status(401).body(new ApiError("Contraseña incorrecta"));
            } catch (DisabledException e) {
                return ResponseEntity.status(403).body(new ApiError("La cuenta está deshabilitada"));
            }

            // 3) Carga detalles y genera JWT
            final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());

            if (req.getFirebaseToken() != null) {
                firebaseService.saveFirebaseToken(user.getId(), req.getFirebaseToken());
            }

            final String token = jwtTokenUtil.generateToken(userDetails, user.getId());

            // Si tu app espera "jwttoken", anótalo en JwtResponse o cambia el nombre de campo
            return ResponseEntity.ok(new JwtResponse(token, user.getId()));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiError("Error interno al autenticar"));
        }
    }
}
