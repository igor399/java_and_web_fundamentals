import by.epam.lab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);

            final int PURCHASES_NUMBER = 6;
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];
            int indexMaxElement = 0;
            boolean areEqual = true;

            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchaseFactory.getPurchaseFromFactory(sc);

                System.out.println(purchases[i]);

                if (purchases[i].compareTo(purchases[indexMaxElement]) > 0) {
                    indexMaxElement = i;
                }
                if (areEqual) {
                    areEqual = purchases[0].equals(purchases[i]);
                }
            }
            System.out.println("purchase with maximum cost - " + purchases[indexMaxElement]);
            System.out.println("are all purchases equal? - " + areEqual);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}