package by.epam.lab;

public class BuisnessTrip {
    private static final int DAILY_ALLOWANCE = 98002;
    private String account;
    private int transportExpenses;
    private int numOfDays;

    public BuisnessTrip() {

    }

    public BuisnessTrip(String account, int transportExpenses, int numOfDays) {
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
        return String.format("%d.%02d", coins / 100, coins % 100);
    }

    public void show() {
        System.out.println("rate = " + currencyConvention(DAILY_ALLOWANCE) +
                "\naccount = " + account +
                "\ntransport = " + currencyConvention(transportExpenses) +
                "\ndays = " + numOfDays +
                "\ntotal = " + currencyConvention(getTotal()));
    }

    @Override
    public String toString() {
        return account + "; " + currencyConvention(transportExpenses) + "; " + numOfDays + "; " + currencyConvention(getTotal());
    }
}
