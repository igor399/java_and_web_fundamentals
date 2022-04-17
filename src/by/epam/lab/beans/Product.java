package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class Product implements Priceable {
    private String name;
    private Byn price;

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    @Override
    public Byn getPrice() {
        return price;
    }

    protected String fieldToString() {
       return getClass().getSimpleName() + SEMICOLON + name + SEMICOLON + price;
    }

    @Override
    public String toString() {
        return fieldToString() + SEMICOLON + getPrice();
    }
}
