package by.epam.lab.services;

import by.epam.lab.beans.Trial;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

import static by.epam.lab.services.GlobalConstants.*;

public class TrialWriter implements Runnable {
    private final Queue<Trial> trialsBuffer;
    private final String path;
    private volatile boolean isDoneWriter;

    public TrialWriter(Queue<Trial> trialsBuffer, String path) {
        this.trialsBuffer = trialsBuffer;
        this.path = path;
    }

    public void isWriterStop() {
        isDoneWriter = true;
        System.out.println(STOP_WRITE);
    }

    @Override
    public void run() {
        System.out.println(START_WRITER);
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(path))) {
            while (!trialsBuffer.isEmpty() || !isDoneWriter) {
                if (!trialsBuffer.isEmpty()) {
                    Trial trial = trialsBuffer.poll();
                    System.out.println(WRITE_TO_FILE + trial);
                    bufferedWriter.write(trial + NEXT_LINE);
                }
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println(NO_FILE);
        }
        System.out.println(STOP_WRITER);
    }
}
