package by.epam.lab.services;

import by.epam.lab.beans.Drop;
import by.epam.lab.beans.TrialMessage;

import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class Producer implements Runnable {
    private final Drop drop;
    private final Scanner scanner;

    public Producer(Drop drop, Scanner scanner) {
        this.drop = drop;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (scanner.hasNext()) {
            TrialMessage trialMessage = new TrialMessage(scanner.nextLine());
            if (!scanner.hasNext()) {
                trialMessage.setDone(true);
            }
            drop.put(trialMessage);
            System.out.format(GOT, trialMessage.getTrialInfo());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
