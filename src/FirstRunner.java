import by.epam.lab.dao.ResultImplCsv;
import by.epam.lab.services.RoundMethod;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class FirstRunner {
    public static void main(String[] args) {
        try {
            ResultImplCsv csvResultInt = new ResultImplCsv(PROPERTIES_PATH, RoundMethod.INTEGER);
            csvResultInt.importData(CSV1_PATH);
            csvResultInt.printMeanMarks();
            csvResultInt.printCurrentResult();
            csvResultInt.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());
        }
    }
}
