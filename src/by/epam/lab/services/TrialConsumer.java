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
            TrialMessage trialMessage = trialBuffer.take();
            System.out.format(PUT, trialMessage.getTrialInfo());
            if (trialMessage.isDone()) {
                break;
            }
        }
    }
}
