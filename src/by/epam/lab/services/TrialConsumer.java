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
    private final AtomicBoolean isDoneCons;

    public TrialConsumer(BlockingQueue<String> stringsBuffer,
                         Queue<Trial> trialBuffer, AtomicBoolean isDoneCons) {
        this.stringsBuffer = stringsBuffer;
        this.trialsBuffer = trialBuffer;
        this.isDoneCons = isDoneCons;
    }

    @Override
    public void run() {
        System.out.println(START_CONS + Thread.currentThread().getName());
        try {
            while (!stringsBuffer.isEmpty() || !isDoneCons.get()) {
                String stringTrial = stringsBuffer.poll(300, TimeUnit.MILLISECONDS);
                if (stringTrial == null) {
                    continue;
                }
                if (stringTrial.equals(DONE)) {
                    isDoneCons.set(true);
                    break;
                }
                Trial trial = new Trial(stringTrial.split(SEMICOLON));
                if (trial.isPassed()) {
                    trialsBuffer.offer(trial);
                    System.out.println(PUSH_BY_THREAD +
                            Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(STOP_CONS + Thread.currentThread().getName());
    }
}
