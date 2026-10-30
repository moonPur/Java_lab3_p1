import servise.NotificationService;
import servise.ConsoleNotificationService;
import servise.impl.SmsNotification;
import servise.impl.EmailNotification;
import model.CorporateSubscription;
import model.UserSubscription;
import model.Option;
import model.Subscription;
import billing.Billing;
import exception.InvalidSubscriptionException;
import exception.SubscriptionNotFoundException;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new ConsoleNotificationService();
        Billing billing = new Billing(notificationService);

        // --- Подготовка успешных данных ---
        Option k4Option = new Option("Доступ к 4К", 150.0);
        Option sportsOption = new Option("Пакет Спортивных каналов", 299.0);
        Option noAdsOption = new Option("Отключение рекламы", 99.0);

        // Успешная подписка 1
        Subscription sub1 = new UserSubscription("Иван Иванов", 399.0);
        sub1.addOption(k4Option);
        sub1.addOption(noAdsOption);

        // Успешная подписка 2
        Subscription sub2 = new CorporateSubscription("ООО Вектор", 2000.0, 0.15); // 15% скидка
        sub2.addOption(sportsOption);
        sub2.addOption(k4Option);

        // Регистрация в базе
        billing.registerSubscription("ivan@mail.ru", sub1);
        billing.registerSubscription("corporate@vector.com", sub2);

        System.out.println("=== Запуск демонстрации системы ===");

        // Сценарий 1: Успех
        System.out.println("\n--- Сценарий 1: Успешная оплата ---");
        try {
            billing.processPaymentByEmail("ivan@mail.ru");
            billing.processPaymentByEmail("corporate@vector.com");
        } catch (SubscriptionNotFoundException e) {
            System.out.println("Эта строчка не должна выполниться: " + e.getMessage());
        }

        // Сценарий 2: Проверяемое исключение (SubscriptionNotFoundException)
        System.out.println("\n--- Сценарий 2: Попытка оплаты несуществующего Email ---");
        try {
            billing.processPaymentByEmail("fake@mail.com");
        } catch (SubscriptionNotFoundException e) {
            System.err.println("[ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ]: " + e.getMessage());
        }
        System.out.println("-> Доказательство: Программа продолжила работу после обработки ошибки биллинга.");

        // Сценарий 3: Непроверяемое исключение (InvalidSubscriptionException)
        System.out.println("\n--- Сценарий 3: Создание некорректной подписки (отрицательная цена) ---");
        try {
            Subscription badSub = new UserSubscription("Петр Петров", -100.0);
        } catch (InvalidSubscriptionException e) {
            System.err.println("[ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ]: " + e.getMessage());
        }

        System.out.println("\n--- Сценарий 3.2: Создание некорректной подписки (пустое имя) ---");
        try {
            Subscription badSub2 = new UserSubscription("   ", 500.0);
        } catch (InvalidSubscriptionException e) {
            System.err.println("[ПЕРЕХВАЧЕНО ИСКЛЮЧЕНИЕ]: " + e.getMessage());
        }

        System.out.println("\n=== Демонстрация успешно завершена! Система стабильна. ===");
    }
}


