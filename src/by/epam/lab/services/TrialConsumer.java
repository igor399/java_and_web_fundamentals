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
    private final AtomicBoolean isDone;

    public TrialConsumer(BlockingQueue<String> stringsBuffer, Queue<Trial> trialBuffer, AtomicBoolean isDone) {
        this.stringsBuffer = stringsBuffer;
        this.trialsBuffer = trialBuffer;
        this.isDone = isDone;
    }

    @Override
    public void run() {
        System.out.println(START_CONS_MESSAGE + Thread.currentThread().getName());
            while (!stringsBuffer.isEmpty() || !isDone.get()) {
                String stringTrial = stringsBuffer.poll();
                if (stringTrial == null) {
                    continue;
                }
                if (stringTrial.equals(DONE)) {
                    isDone.set(true);
                    break;
                }
                Trial trial = new Trial(stringTrial.split(SEMICOLON));
                if (trial.isPassed()) {
                    trialsBuffer.offer(trial);
                    System.out.println(PUSH_BY_THREAD_MESSAGE + Thread.currentThread().getName());
                }
            }
        System.out.println(STOP_CONS_MESSAGE + Thread.currentThread().getName());
    }
}
