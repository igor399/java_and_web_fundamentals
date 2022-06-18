package by.epam.lab.exceptions;

public class LoadRuntimeException extends RuntimeException {
    public LoadRuntimeException() {
        super();
    }

    public LoadRuntimeException(String message) {
        super(message);
    }

    public LoadRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadRuntimeException(Throwable cause) {
        super(cause);
    }
}
