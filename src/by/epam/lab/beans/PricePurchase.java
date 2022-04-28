package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class PricePurchase extends Purchase {
    private Byn discount;

    public PricePurchase(String productName, Byn price, int numUnits,
                         Byn discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public PricePurchase(PricePurchase pricePurchase) {
        this(pricePurchase.getProductName(), pricePurchase.getPrice(),
                pricePurchase.getNumber(), pricePurchase.discount);
    }

    public PricePurchase(String[] fields) {
        this(getValidPriceDiscountPurchase(fields));
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    private static PricePurchase getValidPriceDiscountPurchase(String[] fields) {
        return new PricePurchase(fields[PRODUCT_PARAM],
                new Byn(fields[PRICE_PARAM]),
                Integer.parseInt(fields[NUMBER_PARAM]),
                new Byn(fields[DISCOUNT_PARAM]));
    }

    @Override
    public Byn getCost() {
        return getPrice().subtract(discount).multiply(getNumber());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + SEMICOLON + discount;
    }
}
