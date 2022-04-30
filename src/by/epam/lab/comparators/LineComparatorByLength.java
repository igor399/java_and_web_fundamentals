package by.epam.lab.comparators;

import by.epam.lab.beans.LineSegment;

import java.util.Comparator;

public class LineComparatorByLength implements Comparator<LineSegment> {
    @Override
    public int compare(LineSegment o1, LineSegment o2) {
        return o1.getLen() - o2.getLen();
    }
}
