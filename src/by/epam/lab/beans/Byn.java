package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;

import static by.epam.lab.services.GlobalConstants.*;

public class Byn  {
    private final int kopecks;

    public Byn() {
        this(0);
    }

    public Byn(int kopecks) {
        this.kopecks = kopecks;
    }

    public Byn(String strCoins) {
        this(Integer.parseInt(strCoins));
    }

    public Byn add(Byn byn) {
        return new Byn(kopecks + byn.kopecks);
    }

    public int getKopecks() {
        return kopecks;
    }

    public Byn subtract(Byn byn) {
        return new Byn(kopecks - byn.kopecks);
    }

    public Byn multiply(int times) {
        return new Byn(kopecks * times);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks, d));
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
        return kopecks + 17;
    }

    @Override
    public String toString() {
        return String.format(REG_EXP, kopecks / MAX_KOPECKS_VALUE, kopecks %
                MAX_KOPECKS_VALUE);
    }
}
