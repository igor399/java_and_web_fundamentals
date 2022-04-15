package by.epam.lab;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private Product product;
    private int numUnits;

    public AbstractPurchase() {
        this(new Product(), 0);
    }

    public AbstractPurchase(Product product, int numUnits) {
        this.product = product;
        this.numUnits = numUnits;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumUnits() {
        return numUnits;
    }

    public void setNumUnits(int numUnits) {
        this.numUnits = numUnits;
    }

    public abstract Byn getNotRoundCost(Byn byn);

    public Byn getCost() {
        return getNotRoundCost(product.getPrice().multiply(numUnits)).round(RoundMethod.FLOOR, 2);
    }

    @Override
    public int compareTo(AbstractPurchase o) {
        return o.getCost().compareTo(getCost());
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + product + ";" + numUnits;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }
}
