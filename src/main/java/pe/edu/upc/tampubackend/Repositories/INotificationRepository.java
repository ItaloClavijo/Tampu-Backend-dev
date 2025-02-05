package pe.edu.upc.tampubackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.tampubackend.Entities.Notification;

import java.util.List;

@Repository
public interface INotificationRepository extends JpaRepository <Notification,Long> {
    List<Notification> findByUserIdOrderByDateDesc (Long user_id);
}
