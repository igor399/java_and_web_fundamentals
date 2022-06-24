import by.epam.lab.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>(Arrays.asList(
                new Trial("Smith", 63, 65),
                new Trial("Simpson", 55, 45),
                new Trial("Jackson", 77, 81),
                new LightTrial("Smith", 51, 52),
                new LightTrial("Simpson", 65, 75),
                new StrongTrial("Jackson", 84, 81),
                new StrongTrial("Smith", 66, 88),
                new ExtraTrial("Simpson", 80, 80, 76),
                new ExtraTrial("Jackson", 60, 65, 59)));

        System.out.println(COLLECTION_CONTENT);
        trials.forEach(System.out::println);

        System.out.print(NUM_OF_PASS_TRIALS);
        System.out.println(trials.stream()
                .filter(Trial::isPassed)
                .count());

        trials.sort(Comparator.comparingInt(Trial::result));

        System.out.println(SUM_TWO_MARKS);
        trials.stream()
                .mapToInt(Trial::result)
                .forEach(System.out::println);

        System.out.println(COLLECTION_UNPASSED_RESET_TRIALS);
        List<Trial> unpassedTrials = trials.stream()
                .filter(trial -> !trial.isPassed())
                .map(Trial::getCopy)
                .peek(Trial::clearMarks)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.print(ALL_FAILED_TRIALS);
        System.out.println(unpassedTrials.stream()
                .noneMatch(Trial::isPassed));

        System.out.println(ARR_OF_TWO_MARKS);
        int[] arrOfSums = trials.stream()
                .mapToInt(Trial::result)
                .toArray();
        System.out.println(Arrays.stream(arrOfSums)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(SEPARATOR)));
    }
}
