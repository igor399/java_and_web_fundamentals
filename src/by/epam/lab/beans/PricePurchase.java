package by.epam.lab.beans;

import by.epam.lab.exceptions.*;

import static by.epam.lab.services.GlobalConstants.*;

public class PricePurchase extends Purchase {
    private final Byn discount;

    public PricePurchase(String productName, Byn price, int numUnits, Byn discount) {
        super(productName, price, numUnits);
        if (discount.compareTo(new Byn()) == 0 ) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        if (getPrice().compareTo(discount) <= 0){
            throw new DiscountMoreOrEqualPriceException(WRONG_ARGS_NUM);
        }
        this.discount = discount;
    }

    public PricePurchase(PricePurchase pricePurchase) {
        this(pricePurchase.getProductName(), pricePurchase.getPrice(),
                pricePurchase.getNumber(), pricePurchase.discount);
    }

    public PricePurchase(String[] fields) {
        this(getValidPriceDiscountPurchase(fields));
    }

    private static PricePurchase getValidPriceDiscountPurchase(String[] fields) {
        if (fields.length != DISCOUNT_PURCHASE_FIELDS_NUMBER) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        return new PricePurchase(fields[PRODUCT_PARAM], new Byn(fields[PRICE_PARAM]),
                Integer.parseInt(fields[NUMBER_PARAM]), new Byn(fields[DISCOUNT_PARAM]));
    }

    @Override
    public PricePurchase getCopy(){
        return new PricePurchase(this);
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
