package by.epam.lab;

public class Product {
    private final String name;
    private final Byn price;

    public Product() {
        this("", new Byn(0));
    }

    public Product(String productName, Byn price) {
        this.name = productName;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ";" + price;
    }
}
