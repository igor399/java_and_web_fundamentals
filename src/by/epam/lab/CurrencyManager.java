package by.epam.lab;

public class CurrencyManager {
    public static String toByn(int kopecks) {
        return String.format("%d.%02d", kopecks / 100, kopecks % 100);
    }
}
