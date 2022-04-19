package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;

import static by.epam.lab.services.GlobalConstants.*;

public class Byn implements Comparable<Byn> {
    private final int kopecks;

    public Byn(Byn byn) {
        this(byn.kopecks);
    }

    public Byn(int kopecks) {
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

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks * k, d));
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks, d));
    }

    public int getIntValue() {
        return kopecks;
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
        return String.format(REG_EXP, kopecks / MAX_KOPECKS_VALUE,
                kopecks % MAX_KOPECKS_VALUE);
    }
}
