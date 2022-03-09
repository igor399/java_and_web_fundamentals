package by.epam.lab;

import java.util.Scanner;

public class Byn implements Comparable<Byn> {
    private int value;

    public Byn() {
    }

    public Byn(int value) {
        this.value = value;
    }

    public Byn(Byn byn) {
        this.value = byn.value;
    }

    public Byn add(int value) {
        this.value += value;
        return this;
    }

    public Byn subtract(int value) {
        this.value -= value;
        return this;
    }

    public Byn multiply(int value) {
        this.value *= value;
        return this;
    }

    public Byn multiply(double value) {
        this.value = (int) Math.round(this.value * value);
        return this;
    }

    public Byn divide(int value) {
        this.value /= value;
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
