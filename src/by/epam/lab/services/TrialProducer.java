package by.epam.lab.services;

import by.epam.lab.beans.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialProducer implements Runnable {
    private final TrialBuffer trialBuffer;
    private final String path;

    public TrialProducer(TrialBuffer trialBuffer, String path) {
        this.trialBuffer = trialBuffer;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(path))) {
            while (sc.hasNext()) {
                Trial trial = new Trial(sc.nextLine().split(SEMICOLON));
                trialBuffer.put(trial);
                System.out.format(GOT, trial);
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        } finally {
            trialBuffer.put(FAKE_TRIAL);
        }
    }
}
