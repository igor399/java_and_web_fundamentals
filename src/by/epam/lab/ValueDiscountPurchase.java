package by.epam.lab;

public class ValueDiscountPurchase extends Purchase {
    private int discount;

    public ValueDiscountPurchase() {
    }

    public ValueDiscountPurchase(String productName, Byn price, int numUnits, int discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public ValueDiscountPurchase(String productName, int price, int numUnits, int discount) {
        this(productName, new Byn(price), numUnits, discount);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Byn getCost() {
        Byn copy = new Byn(getPrice());
        return (copy.subtract(discount).multiply(getNumUnits()));
    }

    @Override
    public String toString() {
        return purchaseInString() + ";" + discount + ";" + getCost();
    }
}
