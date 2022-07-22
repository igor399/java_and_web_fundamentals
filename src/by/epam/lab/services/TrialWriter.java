package by.epam.lab.services;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Deque;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialWriter implements Runnable {
    private final Deque<Trial> trialBuffer;
    private final String path;

    public TrialWriter(Deque<Trial> trialBuffer, String path) {
        this.trialBuffer = trialBuffer;
        this.path = path;
    }


    @Override
    public void run() {
        System.out.println("TrialWriter start ");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            while (!trialBuffer.isEmpty()) {
                if (!trialBuffer.isEmpty()) {
                    Trial trial = trialBuffer.pop();
                    bufferedWriter.write(String.valueOf(trial));

                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(NO_FILE);
        }
        System.out.println("TrialWriter stop writing");
    }
}
