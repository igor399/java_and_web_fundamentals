import by.epam.lab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final Product PRODUCT = new Product("Mango", new Byn(500));

        AbstractPurchase[] purchases = {
                new PriceDiscountPurchase(PRODUCT, 1, new Byn(0)),
                new PriceDiscountPurchase(PRODUCT, 3, new Byn(50)),
                new PercentDiscountPurchase(PRODUCT, 20, 9.3),
                new PercentDiscountPurchase(PRODUCT, 6, 5.5),
                new TransportExpensesPurchase(PRODUCT, 2, new Byn(13)),
                new TransportExpensesPurchase(PRODUCT, 5, new Byn(325))
        };

        printPurchasesContent(purchases);

        Arrays.sort(purchases);

        printPurchasesContent(purchases);

        System.out.println("Minimum cost = " + purchases[purchases.length - 1].getCost());

        int index = search(purchases, new Byn(500));
        if (index >= 0) {
            System.out.println("Required purchase is " + purchases[index]);
        } else {
            System.out.println("Required purchase is not found");
        }
    }

    private static void printPurchasesContent(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
        System.out.println();
    }

    private static int search(AbstractPurchase[] purchases, Byn kopecks) {
        return Arrays.binarySearch(purchases, new PriceDiscountPurchase(new Product("", new Byn(kopecks)), 1, new Byn(0)));
    }
}
