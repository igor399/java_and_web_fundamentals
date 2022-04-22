package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class Byn implements Comparable<Byn> {
    private int kopecks;

    public Byn() {
        this(0);
    }

    public Byn(int kopecks) {
        this.kopecks = kopecks;
    }

    public Byn(String strCoins) {
        this(Integer.parseInt(strCoins));
    }

    public int getKopecks() {
        return kopecks;
    }

    public void setKopecks(int kopecks) {
        this.kopecks = kopecks;
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
    public int compareTo(Byn o) {
        return kopecks - o.kopecks;
    }

    @Override
    public String toString() {
        return String.format(REG_EXP, kopecks / MAX_KOPECKS_VALUE, kopecks %
                MAX_KOPECKS_VALUE);
    }
}
