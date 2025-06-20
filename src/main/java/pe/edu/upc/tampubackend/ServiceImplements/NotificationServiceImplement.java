package pe.edu.upc.tampubackend.ServiceImplements;

import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.Notification;
import pe.edu.upc.tampubackend.Services.INotificationService;

@Service
public class NotificationServiceImplement implements INotificationService {

    @Autowired(required = false)
    private FirebaseMessaging firebaseMessaging;

    @Override
    public String sendNotiByToken(Notification notification) {
        if (firebaseMessaging == null) {
            return "FirebaseMessaging no está configurado en este entorno.";
        }

        com.google.firebase.messaging.Notification notificationFirebase = com.google.firebase.messaging.Notification
                .builder()
                .setTitle(notification.getTitle())
                .setBody(notification.getBody())
                .setImage(notification.getImage())
                .build();

        Message message = Message
                .builder()
                .setToken(notification.getRecipientToken())
                .setNotification(notificationFirebase)
                .putAllData(notification.getData())
                .build();

        try {
            firebaseMessaging.send(message);
            return "La notificación se envió con éxito.";
        } catch (FirebaseException e) {
            e.printStackTrace();
            return "Error al enviar la notificación: " + e.getMessage();
        }
    }
}
