package servise.impl;
import servise.NotificationService;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.printf("[SMS] На номер [%s] отправлено сообщение: %s%n", recipient, message);
    }
}
