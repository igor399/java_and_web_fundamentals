package by.epam.lab;

import java.util.Objects;

public class Purchase implements Comparable<Purchase> {
    private String productName;
    private Byn price;
    private int numUnits;

    public Purchase() {
    }

    public Purchase(String productName, Byn price, int numUnits) {
        this.productName = productName;
        this.price = price;
        this.numUnits = numUnits;
    }

    public Purchase(String productName, int price, int numUnits) {
        this(productName, new Byn(price), numUnits);
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
        return price.clone().multiply(numUnits);
    }

    public String purchaseInString() {
        return productName + "; " + price + "; " + numUnits;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ";" + productName + ";" + price + ";" + numUnits + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return numUnits == purchase.numUnits && Objects.equals(productName, purchase.productName) && Objects.equals(price, purchase.price);
    }

    @Override
    public int compareTo(Purchase o) {
        return getCost().compareTo(o.getCost());
    }
}