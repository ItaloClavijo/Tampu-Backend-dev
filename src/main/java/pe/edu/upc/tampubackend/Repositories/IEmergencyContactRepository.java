package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.tampubackend.Entities.EmergencyContact;

@Repository
public interface IEmergencyContactRepository extends JpaRepository<EmergencyContact, Long> {

    // Si EmergencyContact tiene un campo 'user' (ManyToOne) y el id es 'user.id',
    // usa property traversal con guion bajo:
    EmergencyContact findByUser_Id(Long userId);

    boolean existsByUser_Id(Long userId);

    // Borrado robusto por userId (evita problemas con nombres de propiedades)
    @Modifying
    @Transactional
    @Query("DELETE FROM EmergencyContact ec WHERE ec.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
