package by.epam.lab.dao;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.services.ResultFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultImplCsv implements ResultDao {
    private final ResultFactory resultFactory;
    private final Scanner scanner;

    public ResultImplCsv(String fileDirectory, ResultFactory resultFactory)
            throws SourceException {
        try {
            scanner = new Scanner(new FileReader(fileDirectory));
            this.resultFactory = resultFactory;
        } catch (FileNotFoundException e) {
            throw new SourceException(e.getMessage());
        }
    }

    @Override
    public Result nextResult() {
        String[] params = scanner.nextLine().split(SEMICOLON);
        return resultFactory.getResult(params);
    }

    @Override
    public boolean hasResult() {
        return scanner.hasNext();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
