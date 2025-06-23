package pe.edu.upc.tampubackend.Services;



import pe.edu.upc.tampubackend.Entities.Role;

import java.util.List;

public interface IRoleService {

    public Role insert(Role ga);

    public List<Role> list();

    public void delete(Integer id);

    public Role findById(Integer id);

    public List<Role> findByRoleName(String name);
}
