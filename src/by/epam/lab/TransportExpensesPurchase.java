package by.epam.lab;

public class TransportExpensesPurchase extends AbstractPurchase {
    private Byn expenses;

    public TransportExpensesPurchase() {
        this(new Product(), 0, new Byn());
    }

    public TransportExpensesPurchase(Product product, int numUnits, Byn expenses) {
        super(product, numUnits);
        this.expenses = expenses;
    }

    public Byn getExpenses() {
        return expenses;
    }

    public void setExpenses(Byn expenses) {
        this.expenses = expenses;
    }

    @Override
    public Byn getFinalCost(Byn byn) {
        return byn.add(expenses);
    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + ";" + expenses;
    }
}
