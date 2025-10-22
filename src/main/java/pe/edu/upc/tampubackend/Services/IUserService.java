package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.EmergencyContactDTO;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Users;

import java.util.List;

public interface IUserService {

    // ---- Users ----
    List<Users> list();

    void delete(Long idUser);

    Users findById(Long idUser);

    void updateUser(Long idUser, UsersDTO dto);

    // Mantengo el nombre tal cual para no romper implementaciones existentes
    void updateConctact(Long idUser, EmergencyContactDTO dto);

    // Alias correcto (opcional) para uso futuro sin romper nada
    default void updateContact(Long idUser, EmergencyContactDTO dto) {
        updateConctact(idUser, dto);
    }

    void registerUser(UserRegisterDTO dto);

    // ---- Emergency Contact ----
    EmergencyContact find(Long idUser);

    /**
     * Elimina el perfil de un usuario y sus dependencias (por ejemplo, contacto de emergencia).
     *
     * @param userId ID del usuario a eliminar
     * @return true si se eliminó correctamente (y ya no existe en BD), false si falló o no existía
     */
    boolean eliminarPerfil(Long userId);
}
