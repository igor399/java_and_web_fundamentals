import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        final String DELIMITER = ";";
        final String EQUAL = " = ";
        double sum = 0.0;
        int errorsNumb = 0;

        StringBuilder result = new StringBuilder("result()");

        try (Scanner sc = new Scanner(new FileReader("src/in1.csv"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strings = line.split(DELIMITER);
                try {
                    if (strings[0].length() != 0) {
                        double parseDoubleDigit = Double.parseDouble(strings[Integer.parseInt(strings[0])]);
                        sum += parseDoubleDigit;
                        getResult(parseDoubleDigit, result);
                    } else {
                        throw new NoSuchElementException();
                    }
                } catch (NumberFormatException | NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                    errorsNumb++;
                }
            }
            result.append(EQUAL);
            result.append(sum);
            result.append("\n" + "error-lines=");
            result.append(errorsNumb);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    private static void getResult(double number, StringBuilder builder) {
        final int INITIAL_LENGTH = "result()".length();
        final int CURRENT_LENGTH = builder.length();
        final String PLUS = " + ";
        final String MINUS = " - ";

        if (number < 0 && CURRENT_LENGTH != INITIAL_LENGTH) {
            builder.insert(CURRENT_LENGTH - 1, MINUS + number * (-1));
        } else if (CURRENT_LENGTH == INITIAL_LENGTH) {
            builder.insert(CURRENT_LENGTH - 1, number);
        } else {
            builder.insert(CURRENT_LENGTH - 1, PLUS + number);
        }
    }
}
