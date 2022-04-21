package by.epam.lab.beans;

import by.epam.lab.exceptions.*;

import java.util.Objects;

import static by.epam.lab.services.GlobalConstants.*;

public class Purchase {
    private final String productName;
    private final Byn price;
    private final int number;

    public Purchase(String productName, Byn price, int number) {
        if (productName.trim().isEmpty()) {
            throw new IllegalArgumentException(WRONG_ARGS_NUM);
        }
        if (price.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        if (number <= 0) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        this.productName = productName;
        this.price = price;
        this.number = number;
    }

    public Purchase(Purchase purchase) {
        this(purchase.getProductName(), purchase.getPrice(), purchase.getNumber());
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

    public Purchase getCopy(){
        return new Purchase(this);
    }

    private static Purchase getValidPurchase(String[] fields) {
        if (fields.length != PURCHASE_FIELDS_NUMBER) {
            throw new IllegalArgumentException(WRONG_ARGS_NUM);
        }
        return new Purchase(fields[PRODUCT_PARAM], new Byn(fields[PRICE_PARAM]),
                Integer.parseInt(fields[NUMBER_PARAM]));
    }

    protected String fieldsToString() {
        return productName + SEMICOLON + price + SEMICOLON + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return number == purchase.number && Objects.equals(productName,
                purchase.productName) && Objects.equals(price, purchase.price);
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
