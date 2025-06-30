package pe.edu.upc.tampubackend.ServiceImplements;

import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.tampubackend.Entities.Notification;
import pe.edu.upc.tampubackend.Services.INotificationService;


import java.util.Map;

@Service
public class NotificationServiceImplement implements INotificationService {

    @Autowired(required = false)
    private FirebaseMessaging firebaseMessaging;

    @Override
    public void sendNotiByToken(Notification notification) {
        try {
            // Construir la notificación de FCM usando el nombre completo de la clase
            Message.Builder messageBuilder = Message.builder()
                    .setToken(notification.getRecipientToken())
                    .setNotification(
                            com.google.firebase.messaging.Notification.builder()
                                    .setTitle(notification.getTitle())
                                    .setBody(notification.getBody())
                                    .setImage(notification.getImage())
                                    .build()
                    );

            // Añadir datos extra (payload)
            Map<String, String> dataMap = notification.getData();
            if (dataMap != null && !dataMap.isEmpty()) {
                messageBuilder.putAllData(dataMap);
            }

            // Opcional: configuración Android para mostrar imagen correctamente
            if (notification.getImage() != null && !notification.getImage().isEmpty()) {
                AndroidNotification androidNotification = AndroidNotification.builder()
                        .setImage(notification.getImage())
                        .build();
                AndroidConfig androidConfig = AndroidConfig.builder()
                        .setNotification(androidNotification)
                        .build();
                messageBuilder.setAndroidConfig(androidConfig);
            }

            Message message = messageBuilder.build();

            // Enviar la notificación a FCM
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("✅ Notificación enviada a FCM: " + response);

        } catch (Exception e) {
            System.err.println("❌ Error al enviar notificación push: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
