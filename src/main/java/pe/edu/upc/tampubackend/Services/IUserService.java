package pe.edu.upc.tampubackend.Services;

import pe.edu.upc.tampubackend.DTOs.UserRegisterDTO;
import pe.edu.upc.tampubackend.Entities.Users;

import java.time.LocalDate;
import java.util.List;


public interface IUserService {

    public void insert(Users user);

    public List<Users> list();

    public void delete(Long idUser);

    public Users findById(Long idUser);

    public void update(Long idUser,Users user);

    void registerUser(UserRegisterDTO dto);

}
