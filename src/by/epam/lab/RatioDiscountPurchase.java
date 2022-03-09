package by.epam.lab;

public class RatioDiscountPurchase extends Purchase {

    private static final int COUNT_UNITS_FOR_DISCOUNT = 5;
    private double discount;

    public RatioDiscountPurchase() {
    }

    public RatioDiscountPurchase(String productName, Byn price, int numUnits, double discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public RatioDiscountPurchase(String productName, int price, int numUnits, double discount) {
        this(productName, new Byn(price), numUnits, discount);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Byn getCost() {
        Byn result = super.getCost();
        if (getNumUnits() > COUNT_UNITS_FOR_DISCOUNT) {
            result.multiply(1 - getDiscount() / 100);
        }
        return result;
    }

    @Override
    public String toString() {
        return purchaseInString() + ";" + discount + ";" + getCost();
    }

}
