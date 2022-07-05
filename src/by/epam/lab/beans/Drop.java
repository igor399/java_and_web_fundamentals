package by.epam.lab.beans;

public class Drop {
    private TrialMessage trialMessage;
    private boolean empty = true;

    public synchronized TrialMessage take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        empty = true;
        notifyAll();
        return trialMessage;
    }

    public synchronized void put(TrialMessage trialMessage) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        empty = false;
        this.trialMessage = trialMessage;
        notifyAll();
    }
}
