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
    public String toString() {
        return len + SEMICOLON + num;
    }
}
