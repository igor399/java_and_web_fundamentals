import by.epam.lab.dal.ResultsDao;
import by.epam.lab.dal.XmlResultManager;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.MarkRepresentation;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class SecondRunner {
    public static void main(String[] args) {
        try {
            ResultsDao resultsDao = new XmlResultManager(PROPERTIES_PATH, MarkRepresentation.DECIMAL);
            resultsDao.importDataInDB(XML_PATH);
            resultsDao.printMeanMarks();
            resultsDao.printCurrentResult();
            resultsDao.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());
        }
    }
}
