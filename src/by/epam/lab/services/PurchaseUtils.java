package by.epam.lab.services;

import by.epam.lab.beans.*;

import static by.epam.lab.services.GlobalConstants.*;

public class PurchaseUtils<P extends Priceable, N extends Number> {
    private Purchase<P, N> purchase;

    public PurchaseUtils(Purchase<P, N> purchase) {
        this.purchase = purchase;
    }

    public Purchase<P, N> getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase<P, N> purchase) {
        this.purchase = purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(COST + purchase.getCost() + BYN);
    }

    public void printCostDiff(Purchase<? extends Priceable,
            ? extends Number> p) {
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

    @SafeVarargs
    public final void printSameCost(Purchase<? extends Priceable,
            ? extends Number>... purchases) {
        Purchase<? extends Priceable, ? extends Number> equalPurchase = null;
        boolean isSameCost = false;
        for (Purchase<? extends Priceable, ? extends Number> p : purchases) {
            if (purchase.getCost().equals(p.getCost())) {
                isSameCost = true;
                equalPurchase = p;
                break;
            }
        }
        System.out.println(isSameCost ? SAME_COST + equalPurchase : NO_SAME_COST);
    }
}
