package by.epam.lab.dal;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.RoundMethod;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class CsvResultManager extends ResultManager {

    public CsvResultManager(String propDirectory, RoundMethod roundMethod) throws IOException {
        super(propDirectory, roundMethod);
    }

    public void importData(String fileDirectory) throws SqlDbException {
        try (Scanner sc = new Scanner(new FileReader(fileDirectory));
             Connection cn = DriverManager.getConnection(dbUrl, properties)) {
            while (sc.hasNextLine()) {
                String[] param = sc.nextLine().split(SEMICOLON);
                Result result = new Result(param);
                insertResult(cn, result);
            }
        } catch (SQLException e) {
            throw new SqlDbException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
