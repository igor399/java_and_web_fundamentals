package by.epam.lab.exceptions;

public class ParseRuntimeException extends RuntimeException {
    public ParseRuntimeException() {
        super();
    }

    public ParseRuntimeException(String message) {
        super(message);
    }

    public ParseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseRuntimeException(Throwable cause) {
        super(cause);
    }
}
