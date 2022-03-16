package by.epam.lab;

public class Byn implements Comparable<Byn> {
    private final int kopecks;

    public Byn() {
        this(0);
    }

    public Byn(int kopecks) {
        this.kopecks = kopecks;
    }

    public Byn(int rubs, int kopecks) {
        this(rubs * 100 + kopecks);
    }

    public Byn(Byn byn) {
        this(byn.kopecks);
    }

    public int getRubs() {
        return kopecks / 100;
    }

    public Byn add(Byn byn) {
        return new Byn(kopecks + byn.kopecks);
    }

    public Byn subtract(Byn byn) {
        return new Byn(kopecks - byn.kopecks);
    }

    public Byn multiply(int kopecks) {
        return new Byn(this.kopecks * kopecks);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks * k, d));
    }

    public int getCoins() {
        return kopecks % 100;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        return new Byn(roundMethod.round(kopecks, d));
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", getRubs(), getCoins());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return kopecks == byn.kopecks;
    }

    @Override
    public int compareTo(Byn byn) {
        return kopecks - byn.kopecks;
    }
}
