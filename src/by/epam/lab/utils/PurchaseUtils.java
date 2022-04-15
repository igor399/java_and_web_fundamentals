package by.epam.lab.utils;

import by.epam.lab.beans.*;

public class PurchaseUtils {
    private Purchase purchase;

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
        System.out.print("cost = " + purchase.getCost() + " BYN\n");
    }

    public void printCostDiff(Purchase purchase) {
        Byn diff = new Byn(0);
        int result = this.purchase.getCost().compareTo(purchase.getCost());
        if (result > 0) {
            diff = this.purchase.getCost().subtract(purchase.getCost());
            System.out.println("positive difference = " + diff + "BYN\n");
        } else if (result < 0) {
            diff = purchase.getCost().subtract(this.purchase.getCost());
            System.out.println("negative difference = " + diff + "BYN\n");
        } else {
            System.out.println("difference = " + diff + "BYN\n");
        }
    }

    public void printSameCost(Purchase[] purchases) {
        System.out.println("Same cost purchase" + purchase);
        for (Purchase p : purchases) {
            if (purchase.getCost().equals(p.getCost())) {
                System.out.println(p);
            }
        }
    }
}


