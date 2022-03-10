package by.epam.lab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(int rubs, int coins) {
        this(rubs * 100 + coins);
    }

    public Byn(Byn byn) {
        this(byn.value);
    }

    public Byn(Scanner sc) {
        this.value = sc.nextInt();
    }

    public Byn add(Byn byn) {
        this.value += byn.value;
        return this;
    }

    public int getRubs() {
        return value / 100;
    }

    public int getCoins() {
        return value;
    }

    public Byn subtract(Byn byn) {
        this.value -= byn.value;
        return this;
    }

    public Byn multiply(int value) {
        this.value *= value;
        return this;
    }

    public Byn multiply(double k, RoundMethod roundMethod, int d) {
        value = roundMethod.round(value * k, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        value = roundMethod.round(value, d);
        return this;
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", value / 100, value % 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return value == byn.value;
    }

    @Override
    public int compareTo(Byn o) {
        return value - o.value;
    }

}
