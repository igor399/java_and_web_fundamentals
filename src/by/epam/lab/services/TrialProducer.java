package by.epam.lab.services;

import by.epam.lab.beans.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialProducer implements Runnable {
    private final TrialBuffer trialBuffer;
    private final String patch;

    public TrialProducer(TrialBuffer trialBuffer, String patch) {
        this.trialBuffer = trialBuffer;
        this.patch = patch;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(patch))) {
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
