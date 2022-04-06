package by.epam.lab;

public class Purchase {
    protected static final String SEMICOLON = ";";
    protected static final String WRONG_ARG_NUM = "Wrong purchase argument number.";
    protected static final int NAME = 0;
    protected static final int PRICE = 1;
    protected static final int NUMBER = 2;
    private final String  productName;
    private Byn price;
    private int number;


    public Purchase(String productName, Byn price, int number) {
        this.productName = productName;
        setPrice(price);
        setNumber(number);
    }

    public Purchase(Purchase purchase) {
        this(purchase.getProductName(), purchase.getPrice(), purchase.getNumber());
    }

    public Purchase(String[] fields) {
        this(getValidPurchase(fields));
    }

    private static Purchase getValidPurchase(String[] fields) {
        if (fields.length != 3) {
            throw new IllegalArgumentException(WRONG_ARG_NUM);
        }
        return new Purchase(fields[NAME], new Byn(Integer.parseInt(fields[PRICE])), Integer.parseInt(fields[NUMBER]));
    }

    public String getProductName() {
        return productName;
    }

    public Byn getPrice() {
        return price;
    }

    public final void setPrice(Byn price) {
        if (price == null) {
            throw new NullPointerException();
        }
        if (price.compareTo(new Byn()) == 0) {
            throw new NonPositiveArgumentException();
        }
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public final void setNumber(int number) {
        if (number <= 0) {
            throw new NonPositiveArgumentException();
        }
        this.number = number;
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
