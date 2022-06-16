package by.epam.lab.exceptions;

public class ResourceOpeningException extends RuntimeException {
    public ResourceOpeningException() {
        super();
    }

    public ResourceOpeningException(String message) {
        super(message);
    }

    public ResourceOpeningException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceOpeningException(Throwable cause) {
        super(cause);
    }
}
