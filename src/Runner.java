import by.epam.lab.beans.Drop;
import by.epam.lab.exceptions.CustomInterruptedException;
import by.epam.lab.services.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader(FILE_DIRECTORY))) {
            Drop drop = new Drop();
            Thread thread1 = new Thread(new Producer(drop, scanner));
            Thread thread2 = new Thread(new Consumer(drop));
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }catch (InterruptedException e ){
            System.err.println(THREAD_ERROR + Thread.currentThread().getName());
        }
    }
}
