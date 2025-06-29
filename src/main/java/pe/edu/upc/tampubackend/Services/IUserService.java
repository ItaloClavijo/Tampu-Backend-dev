package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;
import pe.edu.upc.tampubackend.Entities.Users;

import java.time.LocalDate;
import java.util.List;


public interface IUserService {



    public List<Users> list();

    public void delete(Long idUser);

    public Users findById(Long idUser);

    public void update(Long idUser, UserRegisterDTO dto);

    void registerUser(UserRegisterDTO dto);


}
