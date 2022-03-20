import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        final String CSV_NAME = "src/in1.csv";
        StringBuilder result = new StringBuilder();
        try {
            int errorLines = getResult(CSV_NAME, result);
            System.out.println(result);
            System.out.println(errorLines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String DELIMITER = ";";
        final String EQUAL = " = ";
        final String SPACE = " ";
        final String RESULT_HEAD = "";
        final String RESULT_TAIL = "" + SPACE + EQUAL + SPACE;
        final String ERROR_LINES = "error-lines" + EQUAL;

        double numResult = 0.0;
        int errorsNumb = 0;
        try (Scanner sc = new Scanner(new FileReader("src/in1.csv"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strings = line.split(DELIMITER);
                try {
                    if (strings[0].length() != 0) {
                        double parseDoubleDigit = Double.parseDouble(strings[Integer.parseInt(strings[0])]);
                        numResult += parseDoubleDigit;
                    } else {
                        throw new NoSuchElementException();
                    }
                } catch (NumberFormatException | NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                    errorsNumb++;
                }
            }
            result.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(numResult);
            result.append("\n" + ERROR_LINES);
            result.append(errorsNumb);

        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        if (result.length() > 0) {
            final int MINUS_LENGTH = MINUS.length();
            final char CHAR_MINUS = '-';
            String sign = result.substring(0, MINUS_LENGTH);
            result.delete(0, MINUS_LENGTH);
            if (sign.equals(MINUS)) {
                result.insert(0, CHAR_MINUS);
            }
        }
        return errorsNumb;
    }
}
