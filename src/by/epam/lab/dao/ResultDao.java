package by.epam.lab.dao;

import by.epam.lab.beans.Result;

import java.io.Closeable;

public interface ResultDao extends Closeable {
    Result nextResult();

    boolean hasResult();
}
