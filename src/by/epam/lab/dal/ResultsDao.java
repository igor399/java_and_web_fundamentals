package by.epam.lab.dal;


import by.epam.lab.exceptions.SqlDbException;

public interface ResultsDao {

    void importData(String filePath) throws SqlDbException;

    void printMeanMarks();

    void printCurrentResult();

    void printLastOfMonthResult();
}
