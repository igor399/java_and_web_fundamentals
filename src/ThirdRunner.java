import by.epam.lab.dal.ResultsDao;
import by.epam.lab.dal.CsvResultManager;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.MarkRepresentation;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class ThirdRunner {
    public static void main(String[] args) {
        try {
            ResultsDao resultsDao = new CsvResultManager(PROPERTIES_PATH, MarkRepresentation.DECIMAL_HALF);
            resultsDao.importDataInDB(CSV2_PATH);
            resultsDao.printMeanMarks();
            resultsDao.printCurrentResult();
            resultsDao.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());
        }
    }
}
