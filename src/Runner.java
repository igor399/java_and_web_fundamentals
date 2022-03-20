import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        final String CSV_NAME = "src/in1.csv";
        StringBuilder strResult = new StringBuilder();
        int errorLines = getResult(CSV_NAME, strResult);
        System.out.println(strResult);
        System.out.println(errorLines);
    }

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {

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
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] strings = line.split(DELIMITER);
                try {
                    if (strings[0].length() != 0) {
                        double parseDoubleDigit = Double.parseDouble(strings[Integer.parseInt(strings[0])]);
                        numResult += parseDoubleDigit;


                        if (strResult.length() == RESULT_HEAD.length()) {
                            strResult.append(numResult);
                        } else if (numResult < 0) {
                            strResult.append(MINUS).append(Math.abs(numResult));
                        } else {
                            strResult.append(PLUS).append(numResult);
                        }
                    }

                } catch (NumberFormatException | NoSuchElementException | ArrayIndexOutOfBoundsException e) {
                    errorsNumb++;
                }
            }
        }


        strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(numResult);
        strResult.append("\n" + ERROR_LINES);
        strResult.append(errorsNumb);

        return errorsNumb;
    }
}