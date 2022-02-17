package by.epam.lab;

public class BusinessTrip {
    private static final int DAILY_ALLOWANCE = 9800;
    private String account;
    private int transportExpenses;
    private int numOfDays;

    public BusinessTrip() {

    }

    public BusinessTrip(String account, int transportExpenses, int numOfDays) {
        this.account = account;
        this.transportExpenses = transportExpenses;
        this.numOfDays = numOfDays;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getTransportExpenses() {
        return transportExpenses;
    }

    public void setTransportExpenses(int transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getTotal() {
        return transportExpenses + DAILY_ALLOWANCE * numOfDays;
    }

    private static String currencyConvention(int coins) {
        return coins / 100 + "." + String.format("%2d", coins % 100);
    }

    public void show() {
        System.out.println("rate = " + currencyConvention(DAILY_ALLOWANCE));
        System.out.println("account = " + account);
        System.out.println("transport = " + currencyConvention(transportExpenses));
        System.out.println("days = " + numOfDays);
        System.out.println("total = " + currencyConvention(getTotal()));
    }

    @Override
    public String toString() {
        return account + "; " + currencyConvention(transportExpenses) + "; " + numOfDays + "; " + currencyConvention(getTotal());
    }
}
