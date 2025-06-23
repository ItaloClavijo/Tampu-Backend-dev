package pe.edu.upc.tampubackend.DTOs;


import pe.edu.upc.tampubackend.Entities.Users;

public class RoleDTO {
    private int id;
    private String rol;
    private Users userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }
}
