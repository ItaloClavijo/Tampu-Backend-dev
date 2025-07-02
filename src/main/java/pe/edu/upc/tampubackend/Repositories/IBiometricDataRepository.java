package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tampubackend.Entities.BiometricData;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBiometricDataRepository extends JpaRepository<BiometricData, Long> {
    List<BiometricData> findByUserIdOrderByTimestampDesc(Long user_id);

    List<BiometricData> findByNivelAndUserIdOrderByTimestampDesc(Integer nivel, Long userId);

    // Metodo para obtener el último registro por user_id, ordenado por timestamp descendente
    Optional<BiometricData> findTopByUserIdOrderByTimestampDesc(Long userId);
}
