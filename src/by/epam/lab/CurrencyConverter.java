package by.epam.lab;

public class CurrencyConverter {
    private final static String REG_EXP = "%d.%02d";
    public static String toByn(int coins) {
        return String.format(REG_EXP, coins / 100, coins % 100);
    }
}
