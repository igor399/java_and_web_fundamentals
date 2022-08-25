package by.epam.lab.exceptions;

public class InitRuntimeException extends RuntimeException{
    public InitRuntimeException() {
    }

    public InitRuntimeException(String message) {
        super(message);
    }

    public InitRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitRuntimeException(Throwable cause) {
        super(cause);
    }
}
