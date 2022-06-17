package by.epam.lab.services;

import by.epam.lab.exceptions.ConnectionDbException;
import by.epam.lab.exceptions.RuntimeCustomException;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static by.epam.lab.services.GlobalConstants.*;

public class ConnectionDbManager implements Closeable {
    public static final ConnectionDbManager CONNECTION_MANAGER = new ConnectionDbManager();
    private Connection cn;

    private ConnectionDbManager() {

    }

    public Connection getConnection() throws ConnectionDbException, IOException {
        if (cn != null) {
            throw new ConnectionDbException(ERR_TWICE_GET_CONNECTION_EXECUTING);
        }
        try {
            FileReader fr = new FileReader(PROPERTIES_DIRECTORY);
            Properties pr = new Properties();
            pr.load(fr);
            return DriverManager.getConnection(pr.getProperty(DB_URL), pr);
        } catch (SQLException | FileNotFoundException e) {
            throw new ConnectionDbException(e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            System.err.println(ERR_MSG);
        }
    }
}
