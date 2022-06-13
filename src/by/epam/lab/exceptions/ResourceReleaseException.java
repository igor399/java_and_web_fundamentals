package by.epam.lab.exceptions;

public class ResourceReleaseException extends Exception {
    public ResourceReleaseException() {
    }

    public ResourceReleaseException(String message) {
        super(message);
    }

    public ResourceReleaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceReleaseException(Throwable cause) {
        super(cause);
    }

    public ResourceReleaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
