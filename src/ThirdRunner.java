import by.epam.lab.dal.CsvResultManager;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.RoundMethod;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class ThirdRunner {
    public static void main(String[] args) {
        try {
            CsvResultManager csvResultDecimalHalf = new CsvResultManager(PROPERTIES_PATH, RoundMethod.DECIMAL_HALF);
            csvResultDecimalHalf.importData(CSV2_PATH);
            csvResultDecimalHalf.printMeanMarks();
            csvResultDecimalHalf.printCurrentResult();
            csvResultDecimalHalf.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());
        }
    }
}
