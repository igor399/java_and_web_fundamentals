package by.epam.lab.services;

import by.epam.lab.exceptions.RuntimeCustomException;

import java.io.Closeable;
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

    public Connection getConnection() {
        if (cn == null) {
            try (FileReader fr = new FileReader(PROPERTIES_DIRECTORY)) {
                Properties pr = new Properties();
                pr.load(fr);
                cn = DriverManager.getConnection(pr.getProperty(DB_URL), pr);
            } catch (SQLException e) {
                throw new RuntimeCustomException(NO_CONNECTION_EXCEPTION);
            } catch (IOException e) {
                throw new RuntimeCustomException(NO_FILE);
            }
        }
        return cn;
    }

    @Override
    public void close() throws IOException {
        try {
            cn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
