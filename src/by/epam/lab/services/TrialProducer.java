package by.epam.lab.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialProducer implements Runnable {
    private final BlockingQueue<String> stringsBuffer;
    private final String path;
    private final CountDownLatch countDownLatch;

    public TrialProducer(BlockingQueue<String> stringsBuffer,
                         String path, CountDownLatch countDownLatch) {
        this.stringsBuffer = stringsBuffer;
        this.path = path;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(START_PROD + Thread.currentThread().getName());
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNext()) {
                stringsBuffer.put(sc.nextLine());
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            //In case of an error, the thread should not stop
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        } finally {
            countDownLatch.countDown();
        }
        System.out.println(STOP_PROD + Thread.currentThread().getName());
    }
}
