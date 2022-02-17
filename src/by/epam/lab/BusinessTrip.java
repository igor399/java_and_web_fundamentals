package by.epam.lab;

public class BuisnessTrip {
    private static final int dailyAllowance = 9800; // 1BYN = 100coins
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
        return getTransportExpenses() + dailyAllowance * getNumOfDays();
    }

    private static String currencyConvention(int coins) {
        return coins / 100 + "." + String.format("%2d", coins % 100);
    }

    public void show() {
        System.out.println("rate = " + currencyConvention(dailyAllowance));
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
