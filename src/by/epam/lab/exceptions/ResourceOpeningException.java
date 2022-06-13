package by.epam.lab.exceptions;

public class ResourceOpeningException extends Exception{
    public ResourceOpeningException() {
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

    public ResourceOpeningException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
