package by.epam.lab;

import java.util.Scanner;

public class PercentDiscountPurchase extends Purchase {
    private static final int COUNT_UNITS_FOR_DISCOUNT = 5;
    private double discount;

    public PercentDiscountPurchase() {
    }

    public PercentDiscountPurchase(String productName, Byn price, int numUnits, double discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public PercentDiscountPurchase(Scanner sc) {
        super(sc);
        this.discount = sc.nextDouble();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Byn getCost() {
        Byn result = super.getCost();
        if (getNumUnits() > COUNT_UNITS_FOR_DISCOUNT) {
            result.multiply(1 - discount / 100, RoundMethod.ROUND, 0);
        }
        return result;
    }

    protected String purchaseString() {
        return super.purchaseString() + ";" + discount;
    }
}
