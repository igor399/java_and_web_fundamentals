import by.epam.lab.beans.*;
import by.epam.lab.comparators.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            List<LineSegment> linesList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String[] coordinates = sc.nextLine().split(REG_EXP);
                double x1 = Double.parseDouble(coordinates[X1]);
                double y1 = Double.parseDouble(coordinates[Y1]);
                double x2 = Double.parseDouble(coordinates[X2]);
                double y2 = Double.parseDouble(coordinates[Y2]);
                int lineLength = (int) Math.round(Math.sqrt((x1 - x2) *
                        (x1 - x2) + (y1 - y2) * (y1 - y2)));
                LineSegment lineSegment = new LineSegment(lineLength);
                Collections.sort(linesList);
                int searchResult = Collections.binarySearch(linesList,
                        lineSegment);
                if (searchResult < 0) {
                    linesList.add(lineSegment);
                } else {
                    linesList.get(searchResult).increaseByOne();
                }
            }
            linesList.sort(new LineComparatorByNum());
            printList(linesList);
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }

    private static void printList(Collection<LineSegment> lines) {
        for (LineSegment line : lines) {
            System.out.println(line);
        }
    }
}
