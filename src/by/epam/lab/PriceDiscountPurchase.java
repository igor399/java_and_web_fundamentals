package by.epam.lab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {

    private Byn discount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String productName, Byn price, int numUnits, Byn discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public PriceDiscountPurchase(Scanner sc) {
        super(sc);
        this.discount = new Byn(sc);
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    public Byn getCost() {
        return new Byn(getPrice()).subtract(discount).multiply(getNumUnits());
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }

}
