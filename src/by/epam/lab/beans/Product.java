package by.epam.lab.beans;

import by.epam.lab.services.*;
import static by.epam.lab.services.GlobalConstants.*;

public class Product implements Item {
    private final String name;
    private final Byn price;

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
        return name + SEMICOLON + price;
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
