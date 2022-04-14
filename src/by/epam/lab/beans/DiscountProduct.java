package by.epam.lab.beans;

import by.epam.lab.exceptions.*;
import static by.epam.lab.services.GlobalConstants.*;

public class DiscountProduct extends Product {
    private Byn discount;
    private static final String WRONG_ARGS_NUM = "Wrong args number";

    public DiscountProduct(String name, Byn price, Byn discount) {
        super(name, price);
        if (discount.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        if (getPrice().compareTo(discount) <= 0) {
            throw new DiscountMoreOrEqualPriceException(WRONG_ARGS_NUM);
        }
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
