package by.epam.lab.exceptions;

public class SourceException extends Exception {
    public SourceException() {
        super();
    }

    public SourceException(String message) {
        super(message);
    }

    public SourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceException(Throwable cause) {
        super(cause);
    }
}
