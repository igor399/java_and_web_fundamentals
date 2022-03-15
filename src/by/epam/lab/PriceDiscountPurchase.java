package by.epam.lab;

public class PriceDiscountPurchase extends AbstractPurchase {
    private Byn discount;

    public PriceDiscountPurchase() {
        this(new Product(), 0, new Byn());
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
    public Byn getNotRoundCost(Byn byn) {
        return byn.subtract(discount.multiply(getNumUnits()));
    }

    @Override
    public String toString() {
        return super.toString() + "; " + discount;
    }
}
