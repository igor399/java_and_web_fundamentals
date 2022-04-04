package by.epam.lab;

public class Util {
    public static String getRightFormat(int coins) {
        final String RIGHT_FORMAT = "%d.%02d";
        return String.format(RIGHT_FORMAT, coins / 100, coins % 100);
    }
}
