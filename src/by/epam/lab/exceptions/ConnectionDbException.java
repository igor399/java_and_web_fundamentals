package by.epam.lab.exceptions;

public class ConnectionDbException extends Exception {
    public ConnectionDbException() {
    }

    public ConnectionDbException(String message) {
        super(message);
    }

    public ConnectionDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionDbException(Throwable cause) {
        super(cause);
    }

    public ConnectionDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
