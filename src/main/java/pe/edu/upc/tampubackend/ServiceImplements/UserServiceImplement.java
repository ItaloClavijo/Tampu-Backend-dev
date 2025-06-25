package pe.edu.upc.tampubackend.ServiceImplements;

import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
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

    @Override
    public void insert(Users user) {
        uR.save(user);
    }

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
    public void update(Long idUser, Users user) {
        Users u = uR.findById(idUser).orElseThrow();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        uR.save(u);
    }

    @Override
    public void registerUser(UserRegisterDTO dto) {
        if (uR.buscarUsername(dto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe.");
        }

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // ¡Encripta esto en producción!
        user.setEmail(dto.getEmail());
        user.setEdad(dto.getEdad());
        user.setEnabled(true);

        Users savedUser = uR.save(user);

        EmergencyContact contact = new EmergencyContact();
        contact.setNombre(dto.getContactoNombre());
        contact.setTelefono(dto.getContactoTelefono());
        contact.setRelacion(dto.getContactoRelacion());
        contact.setUser(savedUser);

        emergencyContactRepository.save(contact);
    }
}
