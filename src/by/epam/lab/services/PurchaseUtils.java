package by.epam.lab.services;

import by.epam.lab.beans.*;

import static by.epam.lab.services.GlobalConstants.*;

public class PurchaseUtils {
    private final Purchase purchase;

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

    public void printCostDiff(Purchase p) {
        Byn greaterCost = purchase.getCost();
        Byn lesserCost = p.getCost();
        Byn costDiff = greaterCost.subtract(lesserCost);
        int result = greaterCost.getIntValue() - lesserCost.getIntValue();
        if (result > 0) {
            System.out.println(POS_DIF + costDiff + BYN);
        } else if (result < 0) {
            System.out.println(NEG_DIF + costDiff + BYN);
        } else {
            System.out.println(DIF + costDiff + BYN);
        }
    }

    public void printSameCost(Purchase... purchases) {
        Purchase equalPurchase = null;
        boolean isSameCost = false;
        for (Purchase p : purchases) {
            if (purchase.getCost().equals(p.getCost())) {
                isSameCost = true;
                equalPurchase = p;
                break;
            }
        }
        if (isSameCost) {
            System.out.println(SAME_COST + equalPurchase);
        } else {
            System.out.println(NO_SAME_COST);
        }
    }
}
