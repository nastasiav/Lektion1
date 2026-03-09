package org.my.edy.exception;

public class MoneyException extends RuntimeException {
    public MoneyException(String message) {
        super(message);
    }

    public MoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyException(Throwable cause) {
        super(cause);
    }

    public MoneyException() {
    }
}
