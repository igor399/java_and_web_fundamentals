import by.epam.lab.beans.TrialBuffer;
import by.epam.lab.services.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(FILE_DIRECTORY))) {
            TrialBuffer trialBuffer = new TrialBuffer();
            Thread thread1 = new Thread(new TrialProducer(trialBuffer, scanner));
            Thread thread2 = new Thread(new TrialConsumer(trialBuffer));
            thread1.start();
            thread2.start();
            thread1.join();
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }catch (InterruptedException e ){
            throw new CustomInterruptedException(String.format
                    (THREAD_ERROR, Thread.currentThread().getName()));
        }
    }
}
