package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class DiscountProduct extends Product {
    private Byn discount;

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);

        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    protected String fieldToString() {
        return super.fieldToString() + SEMICOLON + discount;
    }

    @Override
    public Byn getPrice() {
        return super.getPrice().subtract(discount);
    }
}
