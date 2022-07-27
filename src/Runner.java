import by.epam.lab.beans.Trial;
import by.epam.lab.services.*;
import exceptions.CountDownException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader(PROP_PATH)) {
            Properties properties = new Properties();
            properties.load(fileReader);

            int buffLength = Integer.parseInt(properties.getProperty(BUF_LENGTH));
            int prodNumb = Integer.parseInt(properties.getProperty(MAX_PROD_NUMB));
            int consNumb = Integer.parseInt(properties.getProperty(MAX_CONS_NUMB));

            BlockingQueue<String> stringsBuffer = new LinkedBlockingQueue<>(buffLength);
            Queue<Trial> trialsBuffer = new ConcurrentLinkedQueue<>();

            ExecutorService producersPool = Executors.newFixedThreadPool(prodNumb);
            ExecutorService consumersPool = Executors.newFixedThreadPool(consNumb);
            ExecutorService writersPool = Executors.newSingleThreadExecutor();

            List<String> filesName = Stream
                    .of(new File(properties.getProperty(FOLDER_NAME)).listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getPath)
                    .collect(Collectors.toList());
            CountDownLatch countDownLatch = new CountDownLatch(filesName.size());

            filesName.forEach(file -> producersPool
                    .submit(new TrialProducer(stringsBuffer, file, countDownLatch)));

            filesName.forEach(file -> consumersPool
                    .submit(new TrialConsumer(stringsBuffer, trialsBuffer)));

            TrialWriter trialWriter = new TrialWriter(trialsBuffer, properties.getProperty(RESULT));
            writersPool.submit(trialWriter);

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new CountDownException();
            }

            IntStream.range(0, consNumb).forEach(i -> stringsBuffer.add(DONE));

            trialWriter.isWriterStop();

            consumersPool.shutdown();
            producersPool.shutdown();
            writersPool.shutdown();
        } catch (IOException e) {
            System.err.println(NO_FILE);
        }
    }
}
