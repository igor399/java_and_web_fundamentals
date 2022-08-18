package by.epam.lab.controller.impl;

import by.epam.lab.controller.NumberDAO;

import java.util.Arrays;
import java.util.List;

public class MemoryImplNumberDAO implements NumberDAO {

    private final List<Double> numbers = Arrays.asList(1.0, 2.3, 3.5);

    @Override
    public List<Double> getNumbers() {
        return numbers;
    }
}
