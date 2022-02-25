import by.epam.lab.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);

            final int PURCHASES_NUMBER = sc.nextInt();
            Purchase[] purchases = new Purchase[PURCHASES_NUMBER];

            for (int i = 0; i < purchases.length; i++) {
                purchases[i] = new Purchase(sc.nextInt(), sc.nextDouble(), sc.nextInt());
            }

            printArrayContent(purchases);

            int totalCost = 0;
            double meanCost = 0.0;
            int totalCostMonday = 0;
            int maxCost = 0;
            WeekDay maxPurchaceWeekday = null;

            for (Purchase purchase : purchases) {
                int cost = purchase.getCost();
                totalCost += cost;
                if (purchase.getWeekDay() == WeekDay.MONDAY) {
                    totalCostMonday += cost;
                }
                if (cost > maxCost) {
                    maxCost = cost;
                    maxPurchaceWeekday = purchase.getWeekDay();
                }
            }
            if (purchases.length > 0) {
                meanCost = (double) totalCost / purchases.length / 100;
            }
            System.out.println("Mean cost = " + String.format("%.3f", meanCost) + "\n"
                    + "Total cost on Mondays = " + Purchase.currencyConvention(totalCostMonday) + "\n"
                    + "The day with the max purchace is " + maxPurchaceWeekday);


            Arrays.sort(purchases);
            printArrayContent(purchases);

            Purchase equalledPurchace = new Purchase(5, 0, null);
            int equalledPurchaceIndex = Arrays.binarySearch(purchases, equalledPurchace);
            if (equalledPurchaceIndex >= 0) {
                System.out.println("Equalled purchace: " + purchases[equalledPurchaceIndex]);
            } else {
                System.out.println("Equalled purchace is't found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
    }

    private static void printArrayContent(Purchase[] purchases) {
        System.out.println("product name " + Purchase.PRODUCT_NAME);
        System.out.println("price = " + Purchase.currencyConvention(Purchase.PRICE));
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }
}
