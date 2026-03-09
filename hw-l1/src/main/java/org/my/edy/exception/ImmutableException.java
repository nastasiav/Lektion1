package org.my.edy.exception;

public class ImmutableException extends RuntimeException {
    public ImmutableException(String message) {
        super(message);
        System.out.println(message);
    }

    public ImmutableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImmutableException(Throwable cause) {
        super(cause);
    }

    public ImmutableException() {
    }
}
