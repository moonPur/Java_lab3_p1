package billing;
// Импорт интерфейс и абстрастный класс

import model.Subscription;
import servise.NotificationService;
import exception.SubscriptionNotFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 * Класс бизнес-логики (Service Layer).
 * Демонстрирует паттерн Стратегия и Внедрение зависимостей (DI).
 */


public class Billing {
    private final Map<String, Subscription> database = new HashMap<>();
    private final NotificationService notificationService;

    public Billing(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Регистрация клиента в базе данных
    public void registerSubscription(String email, Subscription sub) {
        database.put(email, sub);
    }

    // Проведение оплаты по Email
    public void processPaymentByEmail(String email) throws SubscriptionNotFoundException {
        Subscription sub = database.get(email);

        if (sub == null) {
            throw new SubscriptionNotFoundException("Пользователь с email " + email + " не зарегистрирован в SkyService!");
        }

        // Если подписка найдена, рассчитываем цену и отправляем уведомление
        double finalPrice = sub.calculateBillingPrice();
        String message = String.format("Уважаемый %s, списание за подписку прошло успешно. Итоговая сумма: %.2f руб.",
                sub.getOwnerName(), finalPrice);

        notificationService.sendNotification(message, email);
    }
}