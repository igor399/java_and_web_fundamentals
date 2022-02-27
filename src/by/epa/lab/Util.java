package by.epa.lab;

public class Util {
    public static String currencyConvention(int coins) {
        return String.format("%d.%02d", coins / 100, coins % 100);
    }
}
