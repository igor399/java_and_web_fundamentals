package by.epam.lab.comparators;

import by.epam.lab.beans.LineSegment;

import java.util.Comparator;

public class LineComparatorByNum implements Comparator<LineSegment> {
    @Override
    public int compare(LineSegment o1, LineSegment o2) {
        return o2.getNum() - o1.getNum();
    }
}
