package by.epam.lab;

public class PriceDiscountPurchase extends AbstractPurchase {
    private Byn discount;

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(Product product, int numUnits, Byn discount) {
        super(product, numUnits);
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    protected Byn getNotRoundCost(Byn byn) {
        return byn.subtract(discount.multiply(getNumUnits()));
    }

    public String toString() {
        return super.toString() + "; " + discount;
    }
}
