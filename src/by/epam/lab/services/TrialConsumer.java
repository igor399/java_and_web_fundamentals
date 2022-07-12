package by.epam.lab.services;

import by.epam.lab.beans.*;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialConsumer implements Runnable {
    private final TrialBuffer trialBuffer;

    public TrialConsumer(TrialBuffer trialBuffer) {
        this.trialBuffer = trialBuffer;
    }

    @Override
    public void run() {
        while (true) {
            Trial trial = trialBuffer.take();
            if (trial.equals(new Trial())) {
                break;
            }
            System.out.format(PUT, trial);
        }
    }
}
