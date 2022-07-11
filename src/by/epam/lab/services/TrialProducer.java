package by.epam.lab.services;

import by.epam.lab.beans.*;

import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialProducer implements Runnable {
    private final TrialBuffer trialBuffer;
    private final Scanner scanner;

    public TrialProducer(TrialBuffer trialBuffer, Scanner scanner) {
        this.trialBuffer = trialBuffer;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            TrialMessage trialMessage = new TrialMessage(scanner.nextLine());
            if (!scanner.hasNext()) {
                trialMessage.setDone(true);
            }
            trialBuffer.put(trialMessage);
            System.out.format(GOT, trialMessage.getTrialInfo());
        }
    }
}
