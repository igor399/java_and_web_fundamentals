package by.epam.lab.beans;

import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;

public class Product implements Item {
    private final String name;
    private final Byn price;
    private static final String WRONG_ARGS_NUM = "Wrong args number";

    public Product() {
        name = null;
        price = null;
    }

    public Product(String name, Byn price) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(WRONG_ARGS_NUM);
        }
        if (price.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    protected String fieldToString() {
        return name + GlobalConstants.SEMICOLON + price;
    }

    @Override
    public String toString() {
        return fieldToString();
    }

    @Override
    public Byn getPrice() {
        return price;
    }
}
