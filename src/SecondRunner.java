import by.epam.lab.dal.XmlResultManager;
import by.epam.lab.exceptions.SqlDbException;
import by.epam.lab.services.RoundMethod;

import java.io.IOException;

import static by.epam.lab.services.GlobalConstants.*;

public class SecondRunner {
    public static void main(String[] args) {
        try {
            XmlResultManager xmlResultDecimal = new XmlResultManager(PROPERTIES_PATH, RoundMethod.DECIMAL);
            xmlResultDecimal.importData(XML_PATH);
            xmlResultDecimal.printMeanMarks();
            xmlResultDecimal.printCurrentResult();
            xmlResultDecimal.printLastOfMonthResult();
        } catch (IOException | SqlDbException e) {
            System.err.println(e.getMessage());

        }
    }
}
