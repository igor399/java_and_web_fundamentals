package by.epam.lab.exceptions;

public class ResourceReleaseException extends Exception {
    public ResourceReleaseException() {
        super();
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
}
