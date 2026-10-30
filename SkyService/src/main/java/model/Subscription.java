package model;
import exception.InvalidSubscriptionException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Абстрактный базовый класс для всех типов подписок.
 * Задает общую структуру, но делегирует расчет стоимости наследникам.
 */
public abstract class Subscription {
    private final String ownerName;
    private final double basePrice;
    // Список дополнительных опций (услуг)
    private final List<Option> appliedOptions = new ArrayList<>();

    // Регулярное выражение: проверяет, что строка содержит хотя бы один видимый символ
    private static final Pattern EMPTY_PATTERN = Pattern.compile("^\\s*$");

    public Subscription(String ownerName, double basePrice) {
        if (basePrice <= 0) {
            throw new InvalidSubscriptionException("Базовая стоимость подписки должна быть больше нуля! Передано: " + basePrice);
        }
        if (ownerName == null || EMPTY_PATTERN.matcher(ownerName).matches()) {
            throw new InvalidSubscriptionException("Имя владельца подписки не может быть пустым!");
        }
        this.ownerName = ownerName;
        this.basePrice = basePrice;
    }

    public void addOption(Option option) {
        if (option == null) {
            throw new IllegalArgumentException("Нельзя добавить пустую опцию (null)!");
        }
        this.appliedOptions.add(option);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public List<Option> getAppliedOptions() {
        return appliedOptions;
    }

    public abstract double calculateBillingPrice();
}
