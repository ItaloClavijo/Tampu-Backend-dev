package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upc.tampubackend.DTOs.EmergencyContactDTO;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Users;
import pe.edu.upc.tampubackend.Repositories.IEmergencyContactRepository;
import pe.edu.upc.tampubackend.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Services.IUserService;


import java.util.List;

@Service
public class UserServiceImplement implements IUserService {
    @Autowired
    private IUserRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Users> list() {
        return uR.findAll();
    }

    @Override
    public void delete(Long idUsuario) {
        uR.deleteById(idUsuario);
    }

    @Override
    public Users findById(Long idUsuario) {
        return uR.findById(idUsuario).orElse(new Users());
    }

    @Autowired
    private IEmergencyContactRepository emergencyContactRepository;

    @Override
    public void updateUser(Long idUser, UsersDTO dto) {
        Users u = uR.findById(idUser).orElseThrow();
        // Validación de username único
        if (!u.getUsername().equals(dto.getUsername())) {
            if (uR.existsByUsername(dto.getUsername())) {
                throw new RuntimeException("El nombre de usuario '" + dto.getUsername() + "' ya está en uso.");
            }
            u.setUsername(dto.getUsername());
        }

        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setEmail(dto.getEmail());
        u.setEdad(dto.getEdad());
        u.setEnabled(true);
        u.setSexo(dto.getSexo());
        u.setCarrera(dto.getCarrera());

        Users savedUser;
        try {
            savedUser = uR.save(u);
            System.out.println("✅ Usuario actualizado: " + savedUser.getId());
        } catch (Exception e) {
            System.err.println("💥 Error al actualizar el usuario: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateConctact(Long idUser, EmergencyContactDTO dto) {

        // Buscar el usuario
        Users u = uR.findById(idUser).orElseThrow();

        // Buscar el contacto de emergencia existente (si lo hay)
        EmergencyContact contact = emergencyContactRepository.findByUserId(idUser);

        if (contact == null) {
            // Si no existe, crea uno nuevo
            contact = new EmergencyContact();
            contact.setUser(u);
        }

        // Actualiza los campos del contacto de emergencia
        contact.setNombre(dto.getNombre());

        String telefono = dto.getTelefono();
        if (!telefono.startsWith("+51")) {
            telefono = "+51" + telefono.replaceAll("^0+", ""); // Quita ceros iniciales si los hubiera
        }
        contact.setTelefono(telefono);
        contact.setRelacion(dto.getRelacion());
        contact.setApiKey(dto.getApiKey());

        // Guardar o actualizar el contacto
        System.out.println("📤 Guardando o actualizando contacto de emergencia...");
        try {
            emergencyContactRepository.save(contact);
            System.out.println("✅ Contacto de emergencia guardado correctamente.");
        } catch (Exception e) {
            System.err.println("💥 Error al guardar el contacto: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public void registerUser(UserRegisterDTO dto) {
        System.out.println("📥 Iniciando registro de usuario...");

        // 1. Validar existencia de usuario
        System.out.println("🔎 Verificando si el username ya existe: " + dto.getUsername());
        if (uR.existsByUsername(dto.getUsername())) {
            System.err.println("❌ El nombre de usuario ya existe: " + dto.getUsername());
            throw new RuntimeException("El nombre de usuario ya existe.");
        }

        // 2. Crear usuario
        System.out.println("🛠️ Creando objeto Users...");
        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setEdad(dto.getEdad());
        user.setEnabled(true);
        user.setSexo(dto.getSexo());
        user.setCarrera(dto.getCarrera());

        System.out.println("📤 Guardando usuario en la base de datos...");
        Users savedUser;
        try {
            savedUser = uR.save(user);
            System.out.println("✅ Usuario guardado con ID: " + savedUser.getId());
        } catch (Exception e) {
            System.err.println("💥 Error al guardar el usuario: " + e.getMessage());
            throw e;
        }

        // 3. Crear contacto de emergencia
        System.out.println("🛠️ Creando contacto de emergencia...");
        EmergencyContact contact = new EmergencyContact();
        contact.setNombre(dto.getContactoNombre());

        String telefono = dto.getContactoTelefono();
        if (!telefono.startsWith("+51")) {
            telefono = "+51" + telefono.replaceAll("^0+", ""); // Quita ceros iniciales si los hubiera
        }
        contact.setTelefono(telefono);

        contact.setRelacion(dto.getContactoRelacion());
        contact.setUser(savedUser);
        contact.setApiKey(dto.getContactoApiKey());

        System.out.println("📤 Guardando contacto de emergencia...");
        try {
            emergencyContactRepository.save(contact);
            System.out.println("✅ Contacto de emergencia guardado correctamente.");
        } catch (Exception e) {
            System.err.println("💥 Error al guardar el contacto: " + e.getMessage());
            throw e;
        }

        System.out.println("🎉 Registro de usuario completado exitosamente.");
    }

    @Override
    public EmergencyContact find(Long idUser) {

        EmergencyContact contact = emergencyContactRepository.findByUserId(idUser);

        return contact;
    }
}
