package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class NumLen {
    private final int len;
    private int num;

    public NumLen() {
        this(0);
    }

    public NumLen(int len) {
        this.len = len;
        num = 1;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void incNum() {
        num++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NumLen other = (NumLen) o;
        if (len != other.len) return false;
        other.num++;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + len;
        return result;
    }

    @Override
    public String toString() {
        return len + SEMICOLON + num;
    }
}
