package pe.edu.upc.tampubackend.Services;


import pe.edu.upc.tampubackend.Entities.Notification;

public interface INotificationService {

    public void sendNotiByToken(Notification notification);

}
