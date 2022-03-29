import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    private static class FinalValue {
        private int errorLines;
        private double numResult;

        public int getErrorLines() {
            return errorLines;
        }

        public double getNumResult() {
            return numResult;
        }

        public void addSum(double numResult) {
            this.numResult += numResult;
        }

        public void incrementErrCount() {
            errorLines++;
        }
    }

    private static FinalValue getResult(String propName) throws FileNotFoundException {
        FinalValue finalValue = new FinalValue();
        final String KEY_REG_EXP = "index(.*)";
        final String NUM_REG_EXP = "[1-9]\\d*";
        final int TAIL_INDEX = 1;
        final String VALUE = "value";
        final String FILE_NOT_FOUND = "File not found";

        try {
            Pattern keyPattern = Pattern.compile(KEY_REG_EXP);
            Pattern numPattern = Pattern.compile(NUM_REG_EXP);
            ResourceBundle rb = ResourceBundle.getBundle(propName);
            Enumeration<String> keys = rb.getKeys();

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
                            finalValue.addSum(Double.parseDouble(rb.getString(valueIJ).trim()));
                        } catch (NumberFormatException | MissingResourceException e) {
                            finalValue.incrementErrCount();
                        }
                    } else {
                        finalValue.incrementErrCount();
                    }
                }
            }
        } catch (MissingResourceException e) {
            throw new FileNotFoundException(FILE_NOT_FOUND);
        }
        return finalValue;
    }

    @Test(expected = FileNotFoundException.class)
    public void testNoFileName() throws FileNotFoundException {
        getResult("in9");
    }

    @Test
    public void testGetResult() throws FileNotFoundException {
        class TestCase {
            private String propName;
            private int errorLines;
            private double numResult;

            public TestCase(String propName, int errorLines, double numResult) {
                this.propName = propName;
                this.errorLines = errorLines;
                this.numResult = numResult;
            }
        }

        TestCase[] testCases = {
                new TestCase("in1", 3, 8.24),
                new TestCase("in2", 9, 30.242),
                new TestCase("in3", 0, 1.9)
        };

        for (TestCase testCase : testCases) {
            FinalValue result = getResult(testCase.propName);
            Assert.assertEquals(testCase.errorLines, result.getErrorLines(),0);
            Assert.assertEquals(testCase.numResult, result.getNumResult(), 0.000000000000001);
        }
    }
}
