package by.epam.lab.controller.dao.impl;

import by.epam.lab.controller.dao.NumberDAO;
import by.epam.lab.exceptions.InitException;

import javax.servlet.ServletConfig;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.GlobalConstants.*;

public class NumberImplCSV implements NumberDAO {
    private final String path;

    public NumberImplCSV(String params, ServletConfig sc) {
        path = sc.getServletContext().getRealPath(WEB_INF) + params;
    }

    @Override
    public List<Double> getNumbers() throws InitException {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            String line = sc.next();
            return Arrays.stream(line.split(SEMICOLON))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new InitException(e);
        }
    }
}
