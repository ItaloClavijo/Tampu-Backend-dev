package pe.edu.upc.tampubackend.ServiceImplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.Role;
import pe.edu.upc.tampubackend.Repositories.IRoleRepository;
import pe.edu.upc.tampubackend.Services.IRoleService;

import java.util.List;

@Service
public class RoleServiceImplement implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role insert(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> list() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Role> findByRoleName(String name) {
        return roleRepository.findByRol(name);
    }
}
