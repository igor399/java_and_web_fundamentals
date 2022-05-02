import by.epam.lab.beans.*;
import by.epam.lab.comparators.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new FileReader(FILE_NAME))) {
            Set<NumLen> setNumLen = new HashSet<>();
            while (sc.hasNextLine()) {
                String[] coordinates = sc.nextLine().split(REG_EXP);
                double x1 = Double.parseDouble(coordinates[X1]);
                double y1 = Double.parseDouble(coordinates[Y1]);
                double x2 = Double.parseDouble(coordinates[X2]);
                double y2 = Double.parseDouble(coordinates[Y2]);
                int len = (int) Math.round(Math.sqrt((x1 - x2) *
                        (x1 - x2) + (y1 - y2) * (y1 - y2)));
                NumLen numLen = new NumLen(len);
                setNumLen.add(numLen);
            }
            List<NumLen> list = new ArrayList<>(setNumLen);
            Collections.sort(list, new NumComparator());
            for (NumLen line : list) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println(NO_FILE);
        }
    }
}
