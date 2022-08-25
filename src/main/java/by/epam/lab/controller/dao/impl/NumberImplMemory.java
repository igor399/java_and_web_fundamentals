package by.epam.lab.controller.dao.impl;

import by.epam.lab.controller.dao.NumberDAO;

import java.util.Arrays;
import java.util.List;

public class NumberImplMemory implements NumberDAO {

    private final List<Double> numbers = Arrays.asList(-1000.1, 33.65, -100.12,
            0.5, -8.0, 29.0, 500.5, 900.9, -123.14, 77.88, 5.0, 10.11, 15.66,
            1700.88, 13.1, 1.1, 0.01, -999.9, 1.00, 11.11, 7.3, -2.100, 1000.0);

    @Override
    public List<Double> getNumbers() {
        return numbers;
    }
}
