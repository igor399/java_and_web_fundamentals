package by.epam.lab.exception;

public class LineException extends Exception {

    public LineException() {
    }

    public LineException(String message) {
        super("Invalid line >> " + message);
    }

    public LineException(String message, Throwable cause) {
        super(message, cause);
    }

    public LineException(Throwable cause) {
        super(cause);
    }
}
