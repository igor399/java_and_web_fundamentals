package by.epam.lab.services;

import by.epam.lab.beans.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Consumer implements Runnable {
    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        while (true) {
            TrialMessage trialMessage = drop.take();
            System.out.format(PUT, trialMessage.getTrialInfo());
//           try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (trialMessage.isDone()) {
                break;
            }
        }
    }
}
