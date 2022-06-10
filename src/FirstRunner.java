import by.epam.lab.dal.CsvResultManager;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.MarkRepresentation;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class FirstRunner {
    public static void main(String[] args) {
        try {
            CsvResultManager csvResultInt = new CsvResultManager(PROPERTIES_PATH, MarkRepresentation.INTEGER);
            csvResultInt.importData(CSV1_PATH);
            csvResultInt.printMeanMarks();
            csvResultInt.printCurrentResult();
            csvResultInt.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());
        }
    }
}
