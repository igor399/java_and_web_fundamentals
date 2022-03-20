import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        final String CSV_NAME = "src/in1.csv";
        final String ERROR_LINES = "error-lines = ";
        StringBuilder strResult = new StringBuilder();
        int errorLines = getResult(CSV_NAME, strResult);
        System.out.println(strResult);
        System.out.println(ERROR_LINES + errorLines);
    }

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        final String PLUS = " + ";
        final String MINUS = " - ";
        final String DELIMITER = ";";
        final String EQUAL = "=";
        final String SPACE = " ";
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ")" + SPACE + EQUAL + SPACE;

        double numResult = 0.0;
        int errorsNumb = 0;

        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNext()) {
                String[] strings = sc.nextLine().split(DELIMITER);
                try {
                    double parseDoubleDigit = Double.parseDouble(strings[Integer.parseInt(strings[0])]);
                    numResult += parseDoubleDigit;
                    if (parseDoubleDigit < 0) {
                        strResult.append(MINUS).append(Math.abs(parseDoubleDigit));
                    } else {
                        strResult.append(PLUS).append(parseDoubleDigit);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorsNumb++;
                }
            }
            if (strResult.length() > 0) {
                final int MINUS_LENGTH = MINUS.length();
                final char CHAR_MINUS = '-';
                String sign = strResult.substring(0, MINUS_LENGTH);
                strResult.delete(0, MINUS_LENGTH);
                if (sign.equals(MINUS)) {
                    strResult.insert(0, CHAR_MINUS);
                }
            }
        }
        strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(numResult);
        return errorsNumb;
    }
}
