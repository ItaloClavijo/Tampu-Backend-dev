package pe.edu.upc.tampubackend.Controllers;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Security.JwtRequest;
import pe.edu.upc.tampubackend.Security.JwtResponse;
import pe.edu.upc.tampubackend.Security.JwtTokenUtil;
import pe.edu.upc.tampubackend.ServiceImplements.FirebaseService;
import pe.edu.upc.tampubackend.ServiceImplements.JwtUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private FirebaseService firebaseService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception {
        authenticate(req.getUsername(), req.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        final Users user = userDetailsService.getUserByUsername(req.getUsername());

        // Guardar el token de Firebase si fue enviado en la solicitud
        if (req.getFirebaseToken() != null) {
            firebaseService.saveFirebaseToken(user.getId(), req.getFirebaseToken());
        }

        final String token = jwtTokenUtil.generateToken(userDetails, user.getId());

        // Incluir el ID del usuario en la respuesta
        return ResponseEntity.ok(new JwtResponse(token, user.getId()));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}