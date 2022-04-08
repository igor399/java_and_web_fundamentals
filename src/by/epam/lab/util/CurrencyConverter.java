package by.epam.lab.util;

public class CurrencyConverter {
    private final static String REG_EXP = "%d.%02d";
    public static String toByn(int kopecks) {
        return String.format(REG_EXP, kopecks / 100, kopecks % 100);
    }
}
