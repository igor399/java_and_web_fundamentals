package by.epam.lab;

public class PriceDiscountPurchase extends Purchase {
    private int discount;
    private final static String SEMICOLON = ";";

    public PriceDiscountPurchase() {
        super();
    }

    public PriceDiscountPurchase(String productName, int price, int numUnits, int discount) {
        super(productName, price, numUnits);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getCost(int discount) {
        return (getPrice() - discount) * getNumUnits();
    }

    protected String fieldToString() {
        return getProductName() + SEMICOLON + Util.getRightFormat(discount);
    }
}
