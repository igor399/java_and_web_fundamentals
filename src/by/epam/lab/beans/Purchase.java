package by.epam.lab.beans;

import by.epam.lab.exceptions.NonPositiveArgumentException;

import static by.epam.lab.services.GlobalConstants.*;

public class Purchase {
    protected static final int PRODUCT_PARAM = 0;
    protected static final int PRICE_PARAM = 1;
    protected static final int NUMBER_PARAM = 2;
    private static final int PURCHASE_FIELDS_NUMBER = 3;
    private static final String WRONG_ARGS_NUM = "Wrong args number";
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

    private static Purchase getValidPurchase(String[] fields) {
        if (fields.length != PURCHASE_FIELDS_NUMBER) {
            throw new IllegalArgumentException(WRONG_ARGS_NUM);
        }
        return new Purchase(fields[PRODUCT_PARAM], new Byn(fields[PRICE_PARAM]), Integer.parseInt(fields[NUMBER_PARAM]));
    }

    protected String fieldsToString() {
        return productName + SEMICOLON + price + SEMICOLON + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + SEMICOLON + getCost();
    }
}
