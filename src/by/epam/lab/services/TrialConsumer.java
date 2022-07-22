package by.epam.lab.services;

import by.epam.lab.beans.*;

import java.util.Deque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<String> stringsBuffer;
    private final Deque<Trial> trialBuffer;
    private final AtomicBoolean isDone;

    public TrialConsumer(BlockingQueue<String> stringsBuffer, Deque<Trial> trialBuffer, AtomicBoolean isDone) {
        this.stringsBuffer = stringsBuffer;
        this.trialBuffer = trialBuffer;
        this.isDone = isDone;
    }

    @Override
    public void run() {
        System.out.println("TrialConsumer start " + Thread.currentThread().getName());
        try {
            while (!isDone.get()) {
                String stringTrial = stringsBuffer.poll();
                if (stringTrial == null) {
                    continue;
                }
                if (stringTrial.equals("DONE")) {
                    isDone.set(true);
                    break;
                }
                Trial trial = new Trial(stringTrial.split(SEMICOLON));
                if (trial.isPassed()) {
                    trialBuffer.push(trial);
                    System.out.println("Trial pushed ->" + Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println("TrialProducer stop working " + Thread.currentThread().getName());
    }
}
