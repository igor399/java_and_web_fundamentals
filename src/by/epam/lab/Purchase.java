package by.epam.lab;

public class Purchase implements Comparable<Purchase> {
    public static final String PRODUCT_NAME = "apple";
    public static final int PRICE = 250;
    private int unitNumber;
    private double discount;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(int unitNumber, double discount, WeekDay weekDay) {
        this.unitNumber = unitNumber;
        this.discount = discount;
        this.weekDay = weekDay;
    }

    public Purchase(int unitNumber,double discount, int weekDay){
        this(unitNumber, discount,WeekDay.values()[weekDay]);
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public int getCost() {
        return (int) Math.round(PRICE * unitNumber * (100 - discount) / 100 / 100) * 100;
    }

    public static String currencyConvention(int coins) {
        return String.format("%d.%02d", coins / 100, coins % 100);
    }

    @Override
    public String toString() {
        return unitNumber + "; " + discount + "; " + weekDay + "; " + currencyConvention(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return unitNumber - purchase.unitNumber;
    }
}
