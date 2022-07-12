import by.epam.lab.beans.TrialBuffer;
import by.epam.lab.services.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        TrialBuffer trialBuffer = new TrialBuffer();
        Thread threadFirst = new Thread(new TrialProducer(trialBuffer, FILE_PATCH));
        Thread threadSecond = new Thread(new TrialConsumer(trialBuffer));
        threadFirst.start();
        threadSecond.start();
    }
}
