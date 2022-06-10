package by.epam.lab.exceptions;

import java.sql.SQLException;

public class SqlDbException extends SQLException {

    public SqlDbException() {
        super();
    }

    public SqlDbException(String message) {
        super(message);
    }

    public SqlDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlDbException(Throwable cause) {
        super(cause);
    }
}
