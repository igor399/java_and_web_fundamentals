package by.epam.lab.services;

import by.epam.lab.beans.*;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<String> stringsBuffer;
    private final Queue<Trial> trialsBuffer;

    public TrialConsumer(BlockingQueue<String> stringsBuffer,
                         Queue<Trial> trialsBuffer) {
        this.stringsBuffer = stringsBuffer;
        this.trialsBuffer = trialsBuffer;
    }

    @Override
    public void run() {
        System.out.println(START_CONS + Thread.currentThread().getName());
        while (true) {
            try {
                String stringTrial = stringsBuffer.take();
                if (stringTrial == null) {
                    continue;
                }
                if (stringTrial.equals(DONE)) {
                    break;
                }
                Trial trial = new Trial(stringTrial.split(SEMICOLON));
                if (trial.isPassed()) {
                    trialsBuffer.add(trial);
                    System.out.println(PUSH_BY_THREAD +
                            Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                //In case of an error, the thread should not stop
            }
        }

        System.out.println(STOP_CONS + Thread.currentThread().getName());
    }
}
