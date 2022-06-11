package by.epam.lab.exceptions;

import java.sql.SQLException;

public class SqlDbException extends SQLException {

    public SqlDbException(String reason, String SQLState, int vendorCode) {
        super(reason, SQLState, vendorCode);
    }

    public SqlDbException(String reason, String SQLState) {
        super(reason, SQLState);
    }

    public SqlDbException(String reason) {
        super(reason);
    }

    public SqlDbException() {
    }

    public SqlDbException(Throwable cause) {
        super(cause);
    }

    public SqlDbException(String reason, Throwable cause) {
        super(reason, cause);
    }

    public SqlDbException(String reason, String sqlState, Throwable cause) {
        super(reason, sqlState, cause);
    }

    public SqlDbException(String reason, String sqlState, int vendorCode, Throwable cause) {
        super(reason, sqlState, vendorCode, cause);
    }
}
