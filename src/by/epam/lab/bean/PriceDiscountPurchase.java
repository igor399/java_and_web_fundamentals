package by.epam.lab.bean;

import by.epam.lab.exception.DiscountMoreOrEqualPriceException;
import by.epam.lab.exception.NonPositiveArgumentException;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;
    private static final int DISCOUNT_PARAM = 3;
    protected static final int DISCOUNT_PURCHASE_FIELDS_NUMBER = 4;
    private static final String WRONG_ARGS_NUM = "Wrong args number";

    public PriceDiscountPurchase(String productName, Byn price, int numUnits, Byn discount) {
        super(productName, price, numUnits);
        setDiscount(discount);
    }

    public PriceDiscountPurchase(PriceDiscountPurchase priceDiscountPurchase) {
        this(priceDiscountPurchase.getProductName(), priceDiscountPurchase.getPrice(),
                priceDiscountPurchase.getNumber(), priceDiscountPurchase.discount);
    }

    public PriceDiscountPurchase(String[] fields) {
        this(getValidPriceDiscountPurchase(fields));
    }

    public final void setDiscount(Byn discount) {
        if (discount.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        if (getPrice().compareTo(discount) <= 0) {
            throw new DiscountMoreOrEqualPriceException(WRONG_ARGS_NUM);
        }
        this.discount = discount;
    }

    private static PriceDiscountPurchase getValidPriceDiscountPurchase(String[] fields) {
        if (fields.length != DISCOUNT_PURCHASE_FIELDS_NUMBER) {
            throw new NonPositiveArgumentException(WRONG_ARGS_NUM);
        }
        return new PriceDiscountPurchase(fields[PRODUCT_PARAM], new Byn(fields[PRICE_PARAM]),
                Integer.parseInt(fields[NUMBER_PARAM]), new Byn(fields[DISCOUNT_PARAM]));
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
