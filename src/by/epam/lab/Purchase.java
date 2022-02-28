package by.epam.lab;

import java.util.Objects;

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

    public Purchase(int unitNumber, double discount, int weekDay) {
        this(unitNumber, discount, WeekDay.values()[weekDay]);
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
        return (int) Math.round(PRICE * unitNumber * ((100 - discount) / 100) / 100) * 100;
    }

    @Override
    public String toString() {
        return unitNumber + "; " + discount + "; " + weekDay + "; " + Util.currencyConvention(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return unitNumber - purchase.unitNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return unitNumber == purchase.unitNumber && Double.compare(purchase.discount, discount) == 0 && weekDay == purchase.weekDay;
    }
}
