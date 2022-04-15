package by.epam.lab.utils;

import by.epam.lab.beans.*;

public class PurchaseUtils {
    private final static String POS_DIF = "positive difference = ";
    private final static String NEG_DIF = "negative difference = ";
    private final static String DIF = "difference = ";
    private final static String BYN = "BYN";
    private final static String NEXT_LINE = "\n";
    private final static String COST = "cost = ";
    private final static String SAME_COST = "same cost purchase: ";
    private final static String NO_SAME_COST = "no same cost purchase: ";
    private final Purchase purchase;

    public PurchaseUtils() {
        purchase = null;
    }

    public PurchaseUtils(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.print(COST + purchase.getCost() + BYN + NEXT_LINE);
    }

    public void printCostDiff(Purchase purchase) {
        Byn diff = new Byn(0);
        int result = this.purchase.getCost().compareTo(purchase.getCost());
        if (result > 0) {
            diff = this.purchase.getCost().subtract(purchase.getCost());
            System.out.println(POS_DIF + diff + BYN);
        } else if (result < 0) {
            diff = purchase.getCost().subtract(this.purchase.getCost());
            System.out.println(NEG_DIF + diff + BYN);
        } else {
            System.out.println(DIF + diff + BYN);
        }
    }

    public void printSameCost(Purchase[] purchases) {
        for (Purchase equalPurchase : purchases) {
            if (purchase.getCost().equals(equalPurchase.getCost())) {
                System.out.println(SAME_COST + purchase);
            }
        }
        System.out.println(NO_SAME_COST);
    }
}
