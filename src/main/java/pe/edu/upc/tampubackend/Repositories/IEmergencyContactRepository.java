package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;

import java.util.List;

@Repository
public interface IEmergencyContactRepository extends JpaRepository<EmergencyContact,Long> {
    EmergencyContact findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
