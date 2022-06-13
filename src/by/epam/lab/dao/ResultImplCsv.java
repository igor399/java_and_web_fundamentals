package by.epam.lab.dao;

import by.epam.lab.beans.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultImplCsv implements ResultDao {
    private final ResultFactory resultFactory;
    private final Scanner scanner;

    public ResultImplCsv(String propDirectory, ResultFactory resultFactory) throws IOException {
        try {
            this.scanner = new Scanner(new FileReader(fileDirectory));
            this.resultFactory = resultFactory;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    @Override
    public Result nextResult() {
        String[] param = scanner.nextLine().split(SEMICOLON);
        return resultFactory.getResult(param);
    }

    @Override
    public boolean hasResult() {
        return scanner.hasNext();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
