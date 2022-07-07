package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialMessage {
    private String message;
    private boolean done = false;

    public TrialMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Trial getTrialInfo() {
        return new Trial(message.split(SEMICOLON));
    }
}
