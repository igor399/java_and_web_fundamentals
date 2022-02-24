import by.epam.lab.Purchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);

            final int PURCHASES_NUMBER = sc.nextInt();
            Purchase[ ] purchases = new Purchase[PURCHASES_NUMBER];

            for(int i = 0; i < purchases.length; i++){
                purchases[i] = new Purchase(sc.nextInt(), sc.nextDouble(),sc.nextInt());
            }

            printArrayContent(purchases);


        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }


    }

    private static void printArrayContent(Purchase[]purchases){
        System.out.println("product name " + Purchase.PRODUCT_NAME);
        System.out.println("price = " + Purchase.currencyConvention(Purchase.PRICE));
        for(Purchase purchase : purchases){
            System.out.println(purchases);
        }
    }

}
