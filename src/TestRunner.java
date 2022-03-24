import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestRunner {

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {

        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            final String DELIMITER = ";";
            final String BEFORE_SIGN = " ";
            final String AFTER_SIGN = " ";
            final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
            final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
            final String RESULT_HEAD = "result(";
            final String RESULT_TAIL = ") = ";

            int errorLines = 0;
            double numResult = 0.0;
            while (sc.hasNextLine()) {
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
        final String BEFORE_SIGN = " ";
        final String AFTER_SIGN = " ";
        final String PLUS = BEFORE_SIGN + "+" + AFTER_SIGN;
        final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String EXP_RES1 = RESULT_HEAD + "5.2" + MINUS + "3.14" + PLUS + "0.0" + RESULT_TAIL + "2.06";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in1.csv", strResult);
        Assert.assertEquals(3, errorLine);
        Assert.assertEquals(EXP_RES1, strResult.toString());
    }

    @Test
    public void getResultSecond() throws FileNotFoundException {
        final String BEFORE_SIGN = " ";
        final String AFTER_SIGN = " ";
        final String MINUS = BEFORE_SIGN + "-" + AFTER_SIGN;
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String EXP_RES2 = RESULT_HEAD + "-3.1" + MINUS + "1.0" + RESULT_TAIL + "-4.1";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in2.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals(EXP_RES2, strResult.toString());
    }

    @Test
    public void getResultThird() throws FileNotFoundException {
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String EXP_RES3 = RESULT_HEAD + "0.75" + RESULT_TAIL + "0.75";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in3.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals(EXP_RES3, strResult.toString());
    }

    @Test
    public void getResultFour() throws FileNotFoundException {
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String EXP_RES4 = RESULT_HEAD + "0.0" + RESULT_TAIL + "0.0";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in4.csv", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals(EXP_RES4, strResult.toString());
    }

    @Test
    public void getResultFive() throws FileNotFoundException {
        final String RESULT_HEAD = "result(";
        final String RESULT_TAIL = ") = ";
        final String EXP_RES5 = RESULT_HEAD + RESULT_TAIL + "0.0";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("src/in5.csv", strResult);
        Assert.assertEquals(1, errorLine);
        Assert.assertEquals(EXP_RES5, strResult.toString());
    }
}
