package by.epam.lab.controller.impl;

import by.epam.lab.controller.NumberDAO;

import java.util.Arrays;
import java.util.List;

public class MemoryImplNumberDAO implements NumberDAO {

    private final List<Double> numbers = Arrays.asList(1.22, 33.65, -100.12, 0.5,
            8.0, 29.0, 500.5, 900.9, 123.14, 77.88);

    @Override
    public List<Double> getNumbers() {
        return numbers;
    }
}
