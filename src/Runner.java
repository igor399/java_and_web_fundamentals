import by.epam.lab.beans.Trial;
import by.epam.lab.services.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader(PROP_PATH)) {
            Properties properties = new Properties();
            properties.load(fileReader);

            BlockingQueue<String> stringsBuffer = new ArrayBlockingQueue<>
                    (Integer.parseInt(properties.getProperty(BUF_LENGTH)));
            Queue<Trial> trialsBuffer = new ConcurrentLinkedDeque<>();
            AtomicBoolean consDone = new AtomicBoolean(false);

            ExecutorService producersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_PROD_NUMB)));

            ExecutorService consumersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_CONS_NUMB)));

            ExecutorService writersPool = Executors.newFixedThreadPool
                    (Integer.parseInt(properties.getProperty(MAX_WRITERS_NUMB)));

            List<String> filesName = Stream
                    .of(new File(properties.getProperty(FOLDER_NAME)).listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getPath)
                    .collect(Collectors.toList());
            CountDownLatch countDownLatch = new CountDownLatch(filesName.size());

            for (int i = 0; i < Integer.parseInt(properties.getProperty(MAX_CONS_NUMB)); i++) {
                consumersPool.submit(new TrialConsumer(stringsBuffer, trialsBuffer, consDone));
            }

            for (String file : filesName) {
                producersPool.submit(new TrialProducer(stringsBuffer, file, countDownLatch));
            }

            TrialWriter trialWriter = new TrialWriter(trialsBuffer, properties.getProperty(RESULT));
            writersPool.submit(trialWriter);

            countDownLatch.await();

            stringsBuffer.put(DONE);

            trialWriter.isWriterStop();

            consumersPool.shutdown();
            producersPool.shutdown();
            writersPool.shutdown();
        } catch (IOException e) {
            System.err.println(NO_FILE);
        } catch (InterruptedException ignored) {
        }
    }
}
