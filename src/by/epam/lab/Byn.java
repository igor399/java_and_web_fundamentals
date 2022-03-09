package by.epam.lab;

public class Byn implements Comparable<Byn> {
    private int kopecks;

    public Byn() {
    }

    public Byn(int value) {
        this.kopecks = value;
    }

    public Byn(Byn byn) {
        this.kopecks = byn.kopecks;
    }

    public Byn add(int value) {
        this.kopecks += value;
        return this;
    }

    public Byn subtract(int value) {
        this.kopecks -= value;
        return this;
    }

    public Byn multiply(int value) {
        this.kopecks *= value;
        return this;
    }

    public Byn multiply(double value) {
        this.kopecks = (int) Math.round(this.kopecks * value);
        return this;
    }

    public Byn divide(int value) {
        this.kopecks /= value;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", kopecks / 100, kopecks % 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Byn byn = (Byn) o;

        return kopecks == byn.kopecks;
    }

    @Override
    public int compareTo(Byn o) {
        return kopecks - o.kopecks;
    }
}
