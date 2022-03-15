package by.epam.lab;

public class PercentDiscountPurchase extends AbstractPurchase {
    private static final int COUNT_UNITS_FOR_DISCOUNT = 5;
    private double discount;

    public PercentDiscountPurchase(Product PRODUCT, int numUnits, Byn byn) {
        this(new Product(), 0, 0.0);
    }

    public PercentDiscountPurchase(Product product, int numUnits, double discount) {
        super(product, numUnits);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public Byn getNotRoundCost(Byn byn) {
        if (getNumUnits() > COUNT_UNITS_FOR_DISCOUNT) {
            byn = byn.multiply(1 - discount / 100, RoundMethod.FLOOR, 0);
        }
        return byn;
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + "; " + discount;
    }
}
