package by.epam.lab.dal;


import by.epam.lab.exceptions.SqlDbException;

public interface ResultsDao {
    void importDataInDB(String filePath) throws SqlDbException;
    void printMeanMarks();
    void printCurrentResult();
    void printLastOfMonthResult();
}
