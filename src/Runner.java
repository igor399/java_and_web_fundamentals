import by.epam.lab.beans.Trial;
import by.epam.lab.services.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {

        try (FileReader fileReader = new FileReader(PROPERTIES_DIRECTORY)) {
            Properties properties = new Properties();
            properties.load(fileReader);

            BlockingQueue<String> stringsBuffer = new ArrayBlockingQueue<>
                    (Integer.parseInt(properties.getProperty(BUF_LENGTH)));

            Queue<Trial> trialsBuffer = new ConcurrentLinkedQueue<>();
            AtomicBoolean consDone = new AtomicBoolean(false);

            ExecutorService producersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_PROD_NUMB)));

            ExecutorService consumersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_CONS_NUMB)));

            ExecutorService writersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_WRITERS_NUMB)));




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
