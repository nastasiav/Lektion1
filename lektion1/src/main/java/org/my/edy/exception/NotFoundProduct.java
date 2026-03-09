package org.my.edy.exception;

public class NotFoundProduct extends RuntimeException {
    public NotFoundProduct(String message) {
        super(message);
    }

    public NotFoundProduct(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundProduct(Throwable cause) {
        super(cause);
    }

    public NotFoundProduct() {
    }
}
