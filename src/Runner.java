import by.epam.lab.beans.Drop;
import by.epam.lab.services.Consumer;
import by.epam.lab.services.Producer;

public class Runner {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
