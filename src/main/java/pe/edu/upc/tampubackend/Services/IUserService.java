package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.EmergencyContactDTO;
import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.DTOs.UsersDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Users;

import java.util.List;


public interface IUserService {



    public List<Users> list();

    public void delete(Long idUser);

    public Users findById(Long idUser);

    public void updateUser(Long idUser, UsersDTO dto);

    public void updateConctact(Long idUser, EmergencyContactDTO dto);

    void registerUser(UserRegisterDTO dto);

    public EmergencyContact find(Long idUser);

}
