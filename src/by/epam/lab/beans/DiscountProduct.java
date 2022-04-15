package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class DiscountProduct extends Product {
    private Byn discount;
    private static final String WRONG_ARGS_NUM = "Wrong args number";

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
        return super.fieldToString() + SEMICOLON + getDiscount();
    }

    @Override
    public Byn getPrice() {
        return super.getPrice().subtract(discount);
    }
}
