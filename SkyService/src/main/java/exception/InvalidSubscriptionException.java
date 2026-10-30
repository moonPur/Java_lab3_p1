package exception;

// Непроверяемое исключение (RuntimeException)
public class InvalidSubscriptionException extends RuntimeException {
    public InvalidSubscriptionException(String message) {
        super(message);
    }
}

