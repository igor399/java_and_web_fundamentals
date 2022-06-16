import by.epam.lab.services.RunnerLogic;
import by.epam.lab.services.ResultFactory;

import static by.epam.lab.services.GlobalConstants.*;

public class RunnerInt {
    public static void main(String[] args) {
        RunnerLogic.execute(ResultFactory.INTEGER, CSV1_PATH);
    }
}
