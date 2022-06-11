package by.epam.lab.dal;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.MarkRepresentation;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class CsvResultManager extends ResultManager {

    public CsvResultManager(String propDirectory, MarkRepresentation markRepresentation) throws IOException {
        super(propDirectory, markRepresentation);
    }

    public void importData(String fileDirectory) throws SqlDbException {
        try (Scanner sc = new Scanner(new FileReader(fileDirectory));
             Connection cn = DriverManager.getConnection(dbUrl, properties)) {
            while (sc.hasNextLine()) {
                String[] params = sc.nextLine().split(SEMICOLON);
                ResultWrapper result = new ResultWrapper(getStudentData(params));
                insertResult(cn, result);
            }
        } catch (SQLException e) {
            throw new SqlDbException(e);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private Result getStudentData(String[] params) {
        return new Result(params[0], params[1], Date.valueOf(params[2]),
                (int) Double.parseDouble(params[3]));
    }
}
