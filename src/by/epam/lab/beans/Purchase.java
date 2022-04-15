package by.epam.lab.beans;

import by.epam.lab.services.*;
import static by.epam.lab.services.GlobalConstants.*;

public class Purchase implements Comparable<Purchase>{
    private final Item item;
    private Number quantity;

    public Purchase() {
        item = null;
    }

    public Purchase(Item item, Number quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public Byn getCost() {
        return item.getPrice().multiply(quantity.doubleValue(), RoundMethod.ROUND, 0);
    }

    @Override
    public int compareTo(Purchase o) {
        return this.getCost().compareTo(getCost());
    }

    @Override
    public String toString() {
        return item.toString() + SEMICOLON + quantity + SEMICOLON + getCost();
    }
}
