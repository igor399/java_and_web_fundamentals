package by.epam.lab.beans;

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
