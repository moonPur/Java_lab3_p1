package model;

public class UserSubscription extends Subscription {

    public UserSubscription(String ownerName, double basePrice) {
        super(ownerName, basePrice);
    }

    @Override
    public double calculateBillingPrice() {
        double totalPrice = getBasePrice();
        // В цикле for-each обходим всю коллекцию добавленных опций
        for (Option option : getAppliedOptions()) {
            totalPrice += option.getPrice();
        }
        return totalPrice;
    }
}

