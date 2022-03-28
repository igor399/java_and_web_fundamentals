import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    static final String RESULT = "sum = ";

    private static int getResult(String propName, StringBuilder strResult) {
        ResourceBundle rb = ResourceBundle.getBundle(propName);
        Enumeration<String> keys = rb.getKeys();
        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";
        Pattern keyPattern = Pattern.compile(KEY_REG_EXP);
        Pattern numPattern = Pattern.compile(NUM_REG_EXP);
        final int TAIL_INDEX = 1;
        final String VALUE = "value";
        final int ROUNDING_VALUE = 1000000;

        double numResult = 0.0;
        int errorLines = 0;

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Matcher keyMatcher = keyPattern.matcher(key);
            if (keyMatcher.matches()) {
                String iStr = keyMatcher.group(TAIL_INDEX);
                String jStr = rb.getString(key).trim();
                Matcher iMatcher = numPattern.matcher(iStr);
                Matcher jMatcher = numPattern.matcher(jStr);
                if (iMatcher.matches() && jMatcher.matches()) {
                    String valueIJ = VALUE + iStr + jStr;
                    try {
                        numResult += Double.parseDouble(rb.getString(valueIJ).trim());
                    } catch (NumberFormatException | MissingResourceException e) {
                        errorLines++;
                    }
                } else {
                    errorLines++;
                }
            }
        }
        numResult = Math.round(numResult * ROUNDING_VALUE);
        numResult /= ROUNDING_VALUE;
        strResult.append(RESULT).append(numResult);
        return errorLines;
    }

    @Test
    public void getResultMainScenario() {
        final String EXP_RES1 = RESULT + "8.24";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("in1", strResult);
        Assert.assertEquals(3, errorLine);
        Assert.assertEquals(EXP_RES1, strResult.toString());

        final String EXP_RES2 = RESULT + "30.242";
        StringBuilder strResult1 = new StringBuilder();
        int errorLine1 = getResult("in2", strResult1);
        Assert.assertEquals(9, errorLine1);
        Assert.assertEquals(EXP_RES2, strResult1.toString());

        final String EXP_RES3 = RESULT + "1.9";
        StringBuilder strResult2 = new StringBuilder();
        int errorLine2 = getResult("in3", strResult2);
        Assert.assertEquals(0, errorLine2);
        Assert.assertEquals(EXP_RES3, strResult2.toString());
    }

    private class TestCase {
        private int errorLines;
        private double numResult;

        public TestCase() {
        }

        public TestCase(int errorLines, double numResult) {
            this.errorLines = errorLines;
            this.numResult = numResult;
        }

        public int getErrorLines() {
            return errorLines;
        }

        public double getNumResult() {
            return numResult;
        }
    }

    TestCase[] testCases = {
            new TestCase(3, 8.24),
            new TestCase(9, 30.242),
            new TestCase(0, 1.9)
    };






}





