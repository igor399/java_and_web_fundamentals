package by.epam.lab.beans;

import by.epam.lab.services.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Purchase<P extends Priceable, N extends Number> {
    private P item;
    private N quantity;

    public Purchase(P item, N quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public P getItem() {
        return item;
    }

    public void setItem(P item) {
        this.item = item;
    }

    public N getQuantity() {
        return quantity;
    }

    public void setQuantity(N quantity) {
        this.quantity = quantity;
    }

    public Byn getCost() {
        return item.getPrice().multiply(quantity.doubleValue(),
                RoundMethod.ROUND, 0);
    }

    @Override
    public String toString() {
        return item + SEMICOLON + quantity + SEMICOLON + getCost();
    }
}
