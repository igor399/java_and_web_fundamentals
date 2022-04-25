package by.epam.lab.beans;

import java.util.Objects;

import static by.epam.lab.services.GlobalConstants.*;

public class Purchase {
    private final String productName;
    private final Byn price;
    private final int number;

    public Purchase(String name, Byn price, int number) {
        this.productName = name;
        this.price = price;
        this.number = number;
    }

    public Purchase(Purchase purchase) {
        this(purchase.productName, purchase.price, purchase.number);
    }

    public Purchase(String[] fields) {
        this(getValidPurchase(fields));
    }

    public String getProductName() {
        return productName;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        return price.multiply(number);
    }

    private static Purchase getValidPurchase(String[] fields) {
        return new Purchase(fields[PRODUCT_PARAM], new Byn(fields[PRICE_PARAM]),
                Integer.parseInt(fields[NUMBER_PARAM]));
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + SEMICOLON + productName
                + SEMICOLON + price + SEMICOLON + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return productName.equals(purchase.productName) && price.equals(purchase.price);
    }

    @Override
    public int hashCode() {
        int result = productName != null ? productName.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return fieldsToString() + SEMICOLON + getCost();
    }
}
