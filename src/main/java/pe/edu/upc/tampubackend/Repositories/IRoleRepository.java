package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tampubackend.Entities.Role;


import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    public List<Role> findByRol(String name);
}
