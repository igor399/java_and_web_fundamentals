package by.epam.lab;

public class PercentDiscountPurchase extends AbstractPurchase {
    private static final int COUNT_UNITS_FOR_DISCOUNT = 5;
    private double discount;

    public PercentDiscountPurchase() {
        super();
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
    protected Byn getNotRoundCost(Byn byn) {
        if (getNumUnits() > COUNT_UNITS_FOR_DISCOUNT) {
            byn = byn.multiply(1 - discount / 100, RoundMethod.ROUND, 0)
        }
        return byn;
    }

    protected String purchaseString() {
        return super.toString() + "; " + discount;
    }
}
