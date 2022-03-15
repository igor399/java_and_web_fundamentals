package by.epam.lab;

public class Product {
    private final String productName;
    private final Byn price;

    public Product() {
        this("", new Byn(0));
    }

    public Product(String productName, Byn price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public Byn getPrice() {
        return price;
    }

    protected String purchaseString() {
        return productName + "; " + price;
    }

    @Override
    public String toString() {
        return productName + "; " + price;
    }
}
