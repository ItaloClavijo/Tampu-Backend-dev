package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Normaliza un poco el input
        String uname = username == null ? "" : username.trim();

        // Si tu repo ofrece ignore-case, usa esta línea:
        // Users user = repo.findByUsernameIgnoreCase(uname);
        // Si no, mantén la existente:
        Users user = repo.findByUsername(uname);

        if (user == null) {
            // CP07: esto permitirá al controller responder 404 "Usuario no registrado"
            throw new UsernameNotFoundException("Usuario no registrado");
        }

        // Construye authorities de forma segura
        List<GrantedAuthority> roles = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(rol -> {
                if (rol != null && rol.getRol() != null) {
                    roles.add(new SimpleGrantedAuthority(rol.getRol()));
                }
            });
        }

        // UserDetails con flags reales
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())          // encriptado en DB
                .authorities(roles)
                .disabled(!Boolean.TRUE.equals(user.getEnabled())) // si enabled==false, quedará deshabilitado
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }

    /**
     * Útil para el controller: obtiene el Users o lanza UsernameNotFoundException.
     * Así el controller puede devolver 404 "Usuario no registrado" (CP07).
     */
    public Users getUserByUsername(String username) throws UsernameNotFoundException {
        String uname = username == null ? "" : username.trim();

        // Si tienes ignore-case:
        // Users user = repo.findByUsernameIgnoreCase(uname);
        Users user = repo.findByUsername(uname);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no registrado");
        }
        return user;
    }
}
