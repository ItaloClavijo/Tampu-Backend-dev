package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tampubackend.Entities.BiometricData;

import java.util.List;

@Repository
public interface IBiometricDataRepository extends JpaRepository<BiometricData, Long> {
    List<BiometricData> findByUserIdOrderByTimestampDesc(Long user_id);
}
