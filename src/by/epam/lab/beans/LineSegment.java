package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class LineSegment implements Comparable<LineSegment>{
    private int len;
    private int num;

    public LineSegment() {
        this(0);
    }

    public LineSegment(int len) {
        this.len = len;
        num = 1;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void increaseByOne() {
        num++;
    }

    @Override
    public int compareTo(LineSegment o) {
        return o.len - len;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineSegment that = (LineSegment) o;
        return len == that.len && num == that.num;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + len;
        return result;
    }

    @Override
    public String toString() {
        return len + SEMICOLON + num;
    }
}
