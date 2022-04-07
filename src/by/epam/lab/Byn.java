package by.epam.lab;

public class Byn implements Comparable<Byn> {
    private final int kopecks;

    public Byn() {
        this(0);
    }

    public Byn(int kopecks) {
        if (kopecks < 0) {
            throw new NonPositiveArgumentException();
        }
        this.kopecks = kopecks;
    }


    public Byn add(Byn byn) {
        return new Byn(kopecks + byn.kopecks);
    }

    public Byn subtract(Byn byn) {
        return new Byn(kopecks - byn.kopecks);
    }

    public Byn multiply(int value) {
        return new Byn(kopecks * value);
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