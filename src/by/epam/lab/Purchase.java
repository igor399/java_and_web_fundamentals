package by.epam.lab;

import java.util.Scanner;

public class Purchase implements Comparable<Purchase> {
    private String productName;
    private Byn price;
    private int numUnits;

    public Purchase() {
        this("", new Byn(0), 0);
    }

    public Purchase(String productName, Byn price, int numUnits) {
        this.productName = productName;
        this.price = price;
        this.numUnits = numUnits;
    }

    public Purchase(Scanner sc) {
        productName = sc.next();
        price = new Byn(sc);
        numUnits = sc.nextInt();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public Byn getCost() {
        return new Byn(price).multiply(numUnits);
    }

    protected String purchaseString() {
        return productName + "; " + price + "; " + numUnits;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "; " + purchaseString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(this instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        if (productName != null ? !productName.equals(purchase.productName) : purchase.productName != null)
            return false;
        return price != null ? price.equals(purchase.price) : purchase.price == null;
    }

    @Override
    public int compareTo(Purchase o) {
        return getCost().compareTo(o.getCost());
    }
}
