package by.epam.lab;

public class LineException extends Exception {

    public LineException() {
    }

    public LineException(String message) {
        super(message);
    }

    public LineException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineException(Throwable cause) {
        super(cause);
    }

    public LineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
