package by.epam.lab;

public class BusinessTrip {
    private static int dailyAllowance;
    private Account account;
    private int transportExpenses;
    private int numOfDays;

    public BusinessTrip() {
    }

    public BusinessTrip(Account account, int transportExpenses, int numOfDays) {
        this.account = account;
        this.transportExpenses = transportExpenses;
        this.numOfDays = numOfDays;
    }

    public static int getDailyAllowance() {
        return dailyAllowance;
    }

    public static void setDailyAllowance(int dailyAllowance) {
        BusinessTrip.dailyAllowance = dailyAllowance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
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
        return getTransportExpenses() + getDailyAllowance() * getNumOfDays();
    }

    public String show() {
        String info = "";
        for (Account account : accounts){
            info += String.format("account = %s",getAccount().getFirstName(),getAccount().getLastName());
        }
        return "account = " + getAccount().getFirstName() + " " + getAccount().getLastName() + "\n" +
                "transport = " + getTransportExpenses() + "\n" +
                "days = " + getNumOfDays() + "\n" +
                "total = " + getTotal();
    }

    @Override
    public String toString() {
        return getAccount().getFirstName() + getAccount().getLastName() + ";" + transportExpenses + ";" + numOfDays + ";" + getTotal();
    }


}
