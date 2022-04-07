package by.epam.lab;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;
    private static final int DISCOUNT = 3;

    public PriceDiscountPurchase(String productName, Byn price, int numUnits, Byn discount) {
        super(productName, price, numUnits);
        setDiscount(discount);
    }

    public PriceDiscountPurchase(PriceDiscountPurchase priceDiscountPurchase) {
        this(priceDiscountPurchase.getProductName(), priceDiscountPurchase.getPrice(), priceDiscountPurchase.getNumber(), priceDiscountPurchase.discount);
    }

    public final void setDiscount(Byn discount) {
        if (discount.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException();
        }
        if (getPrice().compareTo(discount) <= 0) {
            throw new DiscountMoreOrEqualPriceException();
        }
        this.discount = discount;
    }

    public PriceDiscountPurchase(String[] fields) {
        this(getPriceDiscountPurchase(fields));
    }

    private static PriceDiscountPurchase getPriceDiscountPurchase(String[] fields) {
        return new PriceDiscountPurchase(fields[PRODUCT_NAME], new Byn(Integer.parseInt(fields[PRICE])), Integer.parseInt(fields[NUMBER]), new Byn(Integer.parseInt(fields[DISCOUNT])));
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
