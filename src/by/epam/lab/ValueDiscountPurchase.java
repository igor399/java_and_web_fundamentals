package by.epam.lab;

public class ValueDiscountPurchase extends Purchase {
    private Byn discount;

    public ValueDiscountPurchase() {
    }

    public ValueDiscountPurchase(String productName, Byn price, int numUnits, int discount) {
        super(productName, price, numUnits);
        this.discount = new Byn(discount);
    }

    public ValueDiscountPurchase(String productName, int price, int numUnits, int discount) {
        this(productName, new Byn(price), numUnits, discount);
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = new Byn(discount);
    }

    @Override
    public Byn getCost() {
        return getPrice().clone().subtract(discount).multiply(getNumUnits());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "; " + getProductName() + "; " + getPrice() + "; " + getNumUnits() + "; " + discount + "; " + getCost();
    }
}
