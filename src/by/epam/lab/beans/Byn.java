package by.epam.lab.beans;

import by.epam.lab.exceptions.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Byn implements Comparable<Byn>{
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
            throw new NonPositiveArgumentException(INV_VALUE + rubs + SEMICOLON + kopecks);
        }
        if (kopecks >= MAX_KOPECKS_VALUE) {
            throw new MoreThanMaxValueException(INV_MAX_VALUE + rubs + SEMICOLON + kopecks);
        }
        return rubs * MAX_KOPECKS_VALUE + kopecks;
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

    public int getIntValue() {
        return kopecks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return kopecks == byn.kopecks;
    }

    @Override
    public int hashCode() {
        return kopecks;
    }

    @Override
    public int compareTo(Byn o) {
        return kopecks - o.kopecks;
    }

    @Override
    public String toString() {
        return String.format(REG_EXP, kopecks / MAX_KOPECKS_VALUE, kopecks %
                MAX_KOPECKS_VALUE);
    }
}
