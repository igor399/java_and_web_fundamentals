import by.epam.lab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        final int PURCHASES_NUMBER = 6;
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);
            Product[] purchases = new Product[PURCHASES_NUMBER];

            Product maxPurchase = new Product();
            boolean areEqual = true;
            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = PurchaseFactory.getPurchaseFromFactory(sc);
                System.out.println(purchases[i]);
                if (purchases[i].getCost().compareTo(maxPurchase.getCost()) > 0) {
                    maxPurchase = purchases[i];
                }
                if (areEqual) {
                    areEqual = purchases[i].equals(purchases[0]);
                }
            }
            System.out.println("The most expensive purchase: " + maxPurchase);
            System.out.println("Equalled purchase: " + areEqual);
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }
}
