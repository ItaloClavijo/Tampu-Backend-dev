package pe.edu.upc.tampubackend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.tampubackend.Entities.Notification;
import pe.edu.upc.tampubackend.Services.INotificationService;

@RestController
@RequestMapping("/noti")
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    @PostMapping
    public String sendNotiByToken(@RequestBody Notification notification){
        return notificationService.sendNotiByToken(notification);
    }

}
