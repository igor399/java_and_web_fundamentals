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
        if (cn == null) {
            try (FileReader fr = new FileReader(PROPERTIES_DIRECTORY)) {
                Properties properties = new Properties();
                properties.load(fr);
                cn = DriverManager.getConnection(properties.getProperty(DB_URL), properties);
            } catch (SQLException | IOException e) {
                throw new RuntimeCustomException(e.getMessage());
            }
        }
        return cn;
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
