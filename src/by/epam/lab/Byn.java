package by.epam.lab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private final int kopecks;

    public Byn() {
        kopecks = 0;
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

    public Byn(Scanner sc) {
        kopecks = sc.nextInt();
    }

    public Byn add(Byn byn) {
        return new Byn(kopecks + byn.kopecks)
    }

    public int getRubs() {
        return kopecks / 100;
    }

    public int getCoins() {
        return kopecks % 100;
    }

    public Byn subtract(Byn byn) {
        return new Byn(kopecks - byn.kopecks);
    }

    public Byn multiply(int kopecks) {
        return new Byn(this.kopecks * kopecks);
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
        kopecks = roundMethod.round(kopecks * k, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        kopecks = roundMethod.round(kopecks, d);
        return this;
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
    public int compareTo(Byn o) {
        return kopecks - o.kopecks;
    }
}
