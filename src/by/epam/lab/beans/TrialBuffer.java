package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialBuffer {
    private Trial trial;
    private boolean empty = true;

    public synchronized Trial take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
                System.err.format(THREAD_ERROR, Thread.currentThread().getName());
            }
        }
        empty = true;
        notifyAll();
        return trial;
    }

    public synchronized void put(Trial trial) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
                System.err.format(THREAD_ERROR, Thread.currentThread().getName());
            }
        }
        empty = false;
        this.trial = trial;
        notifyAll();
    }
}
