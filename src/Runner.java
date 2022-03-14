import by.epam.lab.*;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        final Product PRODUCT = new Product("mango", new Byn(500));

        AbstractPurchase[] purchases = {new PriceDiscountPurchase(PRODUCT, 1, new Byn(0)),
                new PriceDiscountPurchase(PRODUCT, 3, new Byn(50)),
                new PercentDiscountPurchase(PRODUCT, 20, 5.825),
                new PercentDiscountPurchase(PRODUCT, 6, 5.5),
                new TransportExpensesPurchase(PRODUCT, 2, new Byn(90)),
                new TransportExpensesPurchase(PRODUCT, 5, new Byn(90))
        };

        printPurchasesContent(purchases);

        Arrays.sort(purchases);

        printPurchasesContent(purchases);

        System.out.println("Minimum cost = " + purchases[0].getCost());

        AbstractPurchase requiredPurchase = new PriceDiscountPurchase(new Product("", new Byn(500)), 1, new Byn(0));
        int requiredPurchaseIndex = Arrays.binarySearch(purchases, requiredPurchase);
        if (requiredPurchaseIndex >= 0) {
            System.out.println("\n" + "Required purchase is " + purchases[requiredPurchaseIndex]);
        } else {
            System.out.println("\n" + "Required purchase is not found");
        }
    }

    public static void printPurchasesContent(AbstractPurchase[] purchases){
        for(AbstractPurchase purchase : purchases){
            System.out.println(purchase);
        }
        System.out.println();
    }
}
