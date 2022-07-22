package by.epam.lab.services;

import by.epam.lab.beans.Trial;

import java.util.Deque;

public class TrialWriter implements Runnable{
    private final Deque<Trial> trialBuffer;
    private final String path;

    public TrialWriter(Deque<Trial> trialBuffer, String path) {
        this.trialBuffer = trialBuffer;
        this.path = path;
    }


    @Override
    public void run() {



    }
}
