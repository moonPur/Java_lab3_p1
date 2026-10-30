package model;


/**
 * Подписка для бизнес-клиентов. Расчет стоимости зависит от количества рабочих мест.
 */

public class CorporateSubscription extends Subscription {
    private final double discountRate; // например, 0.20 для скидки 20%

    public CorporateSubscription(String ownerName, double basePrice, double discountRate) {
        super(ownerName, basePrice);
        this.discountRate = discountRate;
    }

    @Override
    public double calculateBillingPrice() {
        double totalPrice = getBasePrice();
        for (Option option : getAppliedOptions()) {
            totalPrice += option.getPrice();
        }
        // Применяем корпоративную скидку
        return totalPrice * (1 - discountRate);
    }
}

