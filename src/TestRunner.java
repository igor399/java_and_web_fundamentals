import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestRunner {
    public static void main(String[] args) {
        final String CSV_NAME = "src/in1.csv";
        final String ERROR_LINES = "error-lines = ";
        final String FILE_NOT_FOUND = "File not found";
        StringBuilder strResult = new StringBuilder();
        try {
            int errorLines = getResult(CSV_NAME, strResult);
            System.out.println(strResult);
            System.out.println(ERROR_LINES + errorLines);
        } catch (FileNotFoundException e) {
            System.err.println(FILE_NOT_FOUND);
        }
    }

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            final String BEFORE_SIGN = " ";
            final String AFTER_SIGN = " ";
            final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
            final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
            final String DELIMITER = ";";
            final String RESULT_HEAD = "result(";
            final String RESULT_TAIL = ") = ";

            double numResult = 0.0;
            int errorLines = 0;

            while (sc.hasNext()) {
                String[] words = sc.nextLine().split(DELIMITER);
                try {
                    double number = Double.parseDouble(words[Integer.parseInt(words[0])]);
                    numResult += number;
                    if (number < 0) {
                        strResult.append(MINUS).append(Math.abs(number));
                    } else {
                        strResult.append(PLUS).append(number);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    errorLines++;
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
            strResult.insert(0, RESULT_HEAD).append(RESULT_TAIL).append(numResult);
            return errorLines;
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testNoFile() throws FileNotFoundException {
        int errorLine = getResult("src/in9.csv", new StringBuilder());
    }

    @Test
    public void getResultFirst() throws FileNotFoundException {
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in1.csv", strResult);
        Assert.assertEquals(3, errorLine);
        Assert.assertEquals("result(5.2 - 3.14 + 0.0) = 2.06", strResult.toString());
    }

    @Test
    public void getResultSecond() throws FileNotFoundException {
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in2.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals("result(-3.1 - 1.0) = -4.1", strResult.toString());
    }

    @Test
    public void getResultThird() throws FileNotFoundException {
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in3.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals("result(0.75) = 0.75", strResult.toString());
    }

    @Test
    public void getResultFour() throws FileNotFoundException {
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in4.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals("result(0.0) = 0.0", strResult.toString());
    }

    @Test
    public void getResultFive() throws FileNotFoundException {
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in5.csv", strResult);
        Assert.assertEquals(1, errorLine);
        Assert.assertEquals("result() = 0.0", strResult.toString());
    }
}

