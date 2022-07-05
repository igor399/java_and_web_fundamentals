package by.epam.lab.exceptions;

public class CustomInterruptedException extends RuntimeException {

    public CustomInterruptedException() {
    }

    public CustomInterruptedException(String message) {
        super(message);
    }

    public CustomInterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomInterruptedException(Throwable cause) {
        super(cause);
    }
}
