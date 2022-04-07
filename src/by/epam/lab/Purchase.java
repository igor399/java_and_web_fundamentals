package by.epam.lab;

public class Purchase {
    protected static final String SEMICOLON = ";";
    protected static final int PRODUCT_PARAM = 0;
    protected static final int PRICE_PARAM = 1;
    protected static final int NUMBER_PARAM = 2;
    private String productName;
    private Byn price;
    private int number;

    public Purchase(String productName, Byn price, int number) {
        setProductName(productName);
        setPrice(price);
        setNumber(number);
    }

    public Purchase(Purchase purchase) {
        this(purchase.getProductName(), purchase.getPrice(), purchase.getNumber());
    }

    public Purchase(String[] values) {
        this(getPurchase(values));
    }

    public String getProductName() {
        return productName;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public final void setProductName(String productName) {
        if (productName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.productName = productName;
    }

    public final void setPrice(Byn price) {
        if (price.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException();
        }
        this.price = price;
    }

    public final void setNumber(int number) {
        if (number <= 0) {
            throw new NonPositiveArgumentException();
        }
        this.number = number;
    }

    private static Purchase getPurchase(String[] values) {
        return new Purchase(values[PRODUCT_PARAM], new Byn(Integer.parseInt(values[PRICE_PARAM])),
                Integer.parseInt(values[NUMBER_PARAM]));
    }

    public Byn getCost() {
        return price.multiply(number);
    }

    protected String fieldsToString() {
        return productName + SEMICOLON + price + SEMICOLON + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + SEMICOLON + getCost();
    }
}
