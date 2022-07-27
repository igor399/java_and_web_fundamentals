package by.epam.lab.services;

import by.epam.lab.beans.Trial;
import exceptions.CustomRuntimeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

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
                Trial trial;
                if (((trial = trialsBuffer.poll()) != null)) {
                    System.out.println(WRITE_TO_FILE + trial);
                    bufferedWriter.write(trial + NEXT_LINE);
                } else {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                        //In case of an error, the thread should not stop
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(NO_FILE);
        } catch (CustomRuntimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(STOP_WRITER);
    }
}
