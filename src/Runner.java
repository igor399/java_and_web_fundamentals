import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import by.epam.lab.comparators.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Map<Integer, Integer> mapNumLen = new HashMap<>();
            while (sc.hasNextLine()) {
                String[] coordinates = sc.nextLine().split(REG_EXP);
                Integer lineLength = calculateLineLength(coordinates);
                Integer lineNumb = mapNumLen.get(lineLength);
                if (lineNumb == null) {
                    mapNumLen.put(lineLength, 1);
                } else {
                    mapNumLen.put(lineLength, ++lineNumb);
                }
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>
                    (mapNumLen.entrySet());
            list.sort(new NumComparator());
            for (Map.Entry<Integer, Integer> entry : list) {
                System.out.println(entry.getKey() + SEMICOLON + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }

    public static Integer calculateLineLength(String[] coordinates) {
        double x1 = Double.parseDouble(coordinates[X1]);
        double y1 = Double.parseDouble(coordinates[Y1]);
        double x2 = Double.parseDouble(coordinates[X2]);
        double y2 = Double.parseDouble(coordinates[Y2]);
        return (int) Math.round(Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
                * (y1 - y2)));
    }
}
