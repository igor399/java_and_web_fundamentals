package by.epam.lab.exceptions;

public class NonPositiveArgumentException extends IllegalArgumentException  {
    public NonPositiveArgumentException() {

    }

    public NonPositiveArgumentException(String s) {
        super(s);
    }

    public NonPositiveArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonPositiveArgumentException(Throwable cause) {
        super(cause);
    }
}
