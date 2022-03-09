import by.epam.lab.Purchase;
import by.epam.lab.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);

            Purchase[] purchases = new Purchase[6];
            int maxElementIndex = 0;
            boolean isEquals = true;
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchaseFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);

                if (purchases[i].compareTo(purchases[maxElementIndex]) > 0) {
                    maxElementIndex = i;
                }
                if (!purchases[0].equals(purchases[i])) {
                    isEquals = false;
                }
            }
            System.out.println("The most expensive purchase : " + purchases[maxElementIndex]);
            System.out.println("Equal purachases : " + isEquals);
        } catch (FileNotFoundException e) {
            System.err.println("Input file isn't found");
        }
    }
}
