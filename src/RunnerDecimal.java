import by.epam.lab.services.RunnerLogic;
import by.epam.lab.services.ResultFactory;

import static by.epam.lab.services.GlobalConstants.*;

public class RunnerDecimal {
    public static void main(String[] args) {
        RunnerLogic.execute(ResultFactory.DECIMAL, XML_PATH);
    }
}
