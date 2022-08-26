package by.epam.lab.controller.dao;

import by.epam.lab.exceptions.InitException;

import java.util.List;

public interface NumberDAO {
    List<Double> getNumbers() throws InitException;
}
