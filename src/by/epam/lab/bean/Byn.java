package by.epam.lab.bean;

import by.epam.lab.util.CurrencyConverter;
import by.epam.lab.exception.NonPositiveArgumentException;

public class Byn implements Comparable<Byn> {
    private static final String INV_VALUE = "Invalid value: ";
    private final int kopecks;

    public Byn() {
        this(0);
    }

    public Byn(Byn byn) {
        this(byn.kopecks);
    }

    public Byn(int kopecks) {
        if (kopecks < 0) {
            throw new NonPositiveArgumentException(INV_VALUE + kopecks);
        }
        this.kopecks = kopecks;
    }

    public Byn(String strCoins) {
        this(Integer.parseInt(strCoins));
    }

    public Byn(int rubs, int kopecks) {
        this(getValidValue(rubs, kopecks));
    }

    private static int getValidValue(int rubs, int kopecks) {
        if (rubs < 0 || kopecks < 0) {
            throw new NonPositiveArgumentException(INV_VALUE + CurrencyConverter.toByn(kopecks));
        }
        if (kopecks >= 100) {
            throw new NonPositiveArgumentException(INV_VALUE + CurrencyConverter.toByn(kopecks));
        }
        return rubs * 100 + kopecks;
    }

    public Byn add(Byn byn) {
        return new Byn(kopecks + byn.kopecks);
    }

    public Byn subtract(Byn byn) {
        return new Byn(kopecks - byn.kopecks);
    }

    public Byn multiply(int times) {
        return new Byn(kopecks * times);
    }

    @Override
    public int compareTo(Byn o) {
        return kopecks - o.kopecks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return kopecks == byn.kopecks;
    }

    @Override
    public String toString() {
        return CurrencyConverter.toByn(kopecks);
    }
}
