import by.epam.lab.ExtraTrial;
import by.epam.lab.LightTrial;
import by.epam.lab.StrongTrial;
import by.epam.lab.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static by.epam.lab.services.GlobalConstants.*;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>();
        trials.add(new Trial("Smith", 63, 65));
        trials.add(new Trial("Simpson", 55, 45));
        trials.add(new Trial("Jackson", 77, 81));
        trials.add(new LightTrial("Smith", 51, 52));
        trials.add(new LightTrial("Simpson", 65, 75));
        trials.add(new StrongTrial("Jackson", 84, 81));
        trials.add(new StrongTrial("Smith", 66, 88));
        trials.add(new ExtraTrial("Simpson", 80, 80, 76));
        trials.add(new ExtraTrial("Jackson", 60, 65, 59));

        System.out.println("Collection content:");
        trials.forEach(System.out::println);

        System.out.print("Number of passed trials: ");
        System.out.println(trials.stream()
                .filter(Trial::isPassed)
                .count());

        trials.sort(Comparator.comparing(t -> (t.getMark1() + t.getMark2())));

        System.out.println("Sums of first and second marks from the collection:");
        trials.stream()
                .mapToInt(t -> (t.getMark1() + t.getMark2()))
                .forEach(System.out::println);

        System.out.println("New collection from unpassed trials:");
        List<Trial> unpassedTrials = trials.stream()
                .filter(trial -> !trial.isPassed())
                .map(Trial::copyTrials)
                .peek(Trial::resetMark)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.print("Check all failed trials: ");
        System.out.println(unpassedTrials.stream()
                .noneMatch(Trial::isPassed));

        System.out.println("Array from sums of first and second marks:");
        int[] arrOfSums = trials.stream()
                .mapToInt(t -> t.getMark1() + t.getMark2())
                .toArray();
        System.out.println(Arrays.toString(arrOfSums));
    }
}
