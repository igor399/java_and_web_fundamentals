package by.epam.lab;

public class Purchase {
    private final String productName;
    private final int price;
    private int numUnits;
    private final static String SEMICOLON = ";";

    public Purchase() {
        this("", 0, 0);
    }

    public Purchase(String productName, int price, int numUnits) {
        this.productName = productName;
        this.price = price;
        this.numUnits = numUnits;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public int getCost() {
        return price * numUnits;
    }

    protected String fieldToString() {
        return productName + SEMICOLON + Util.getRightFormat(price) + SEMICOLON + numUnits;
    }

    @Override
    public String toString() {
        return fieldToString() + SEMICOLON + Util.getRightFormat(getCost());
    }
}
