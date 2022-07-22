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

    public TrialConsumer(BlockingQueue<String> stringsBuffer, Deque<Trial>trialBuffer, AtomicBoolean isDone) {
        this.stringsBuffer = stringsBuffer;
        this.trialBuffer = trialBuffer;
        this.isDone = isDone;
    }

    @Override
    public void run() {
        while (true) {
            Trial trial = trialBuffer.take();
            if (trial.equals(FAKE_TRIAL)) {
                break;
            }
            System.out.format(PUT, trial);
        }
    }
}
