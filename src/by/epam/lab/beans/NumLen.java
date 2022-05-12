package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class NumLen {
    private final int len;
    private final int num;

    public NumLen() {
        this(0, 0);
    }

    public NumLen(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NumLen other = (NumLen) o;
        if (len != other.len) return false;
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
