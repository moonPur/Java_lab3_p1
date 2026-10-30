package exception;

// Проверяемое исключение (Exception)
public class SubscriptionNotFoundException extends Exception {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}

