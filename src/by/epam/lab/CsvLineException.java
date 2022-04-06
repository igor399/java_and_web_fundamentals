package by.epam.lab;

public class CsvLineException extends Exception {

    public CsvLineException() {
    }

    public CsvLineException(String message) {
        super(message);
    }

    public CsvLineException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvLineException(Throwable cause) {
        super(cause);
    }

    public CsvLineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
