package exceptions;

public class InterruptedException extends RuntimeException {

    public InterruptedException() {
    }

    public InterruptedException(String message) {
        super(message);
    }

    public InterruptedException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterruptedException(Throwable cause) {
        super(cause);
    }
}
