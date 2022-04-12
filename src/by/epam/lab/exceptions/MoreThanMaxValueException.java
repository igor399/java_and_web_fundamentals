package by.epam.lab.exceptions;

public class MoreThanMaxValueException extends IllegalArgumentException{
    public MoreThanMaxValueException() {
    }

    public MoreThanMaxValueException(String s) {
        super(s);
    }

    public MoreThanMaxValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoreThanMaxValueException(Throwable cause) {
        super(cause);
    }
}
