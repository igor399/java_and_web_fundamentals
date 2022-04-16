package by.epam.lab.beans;

import by.epam.lab.services.*;
import static by.epam.lab.services.GlobalConstants.*;

public class Purchase implements Comparable<Purchase>{
    private Priceable item;
    private Number quantity;

    public Purchase(Priceable item, Number quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Priceable getItem() {
        return item;
    }

    public void setItem(Priceable item) {
        this.item = item;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public Byn getCost() {
        return item.getPrice().multiply(quantity.doubleValue(),
                RoundMethod.ROUND, 0);
    }

    @Override
    public int compareTo(Purchase o) {
        return this.getCost().compareTo(getCost());
    }

    @Override
    public String toString() {
        return item + SEMICOLON + quantity + SEMICOLON + getCost();
    }
}
