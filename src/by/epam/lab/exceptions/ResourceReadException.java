package by.epam.lab.exceptions;

public class ResourceReadException extends RuntimeException {
    public ResourceReadException() {
        super();
    }

    public ResourceReadException(String message) {
        super(message);
    }

    public ResourceReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceReadException(Throwable cause) {
        super(cause);
    }
}
