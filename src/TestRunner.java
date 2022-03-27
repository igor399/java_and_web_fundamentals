import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    final static String RESULT = "sum = ";

    private static int getResult(String propName, StringBuilder strResult) {
        double numResult = 0.0;
        int errorLines = 0;
        ResourceBundle rb = ResourceBundle.getBundle(propName);
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            try {
                Pattern patter = Pattern.compile("index.*");
                Matcher matcher = patter.matcher(key);
                if (matcher.matches()) {
                    String indexKey = matcher.group();
                    String indexKeyNum = getIndexKeyNum(indexKey);
                    String indexValue = getIndexValue(rb.getString(indexKey).trim());
                    String valueNum = rb.getString("value" + indexKeyNum + indexValue);
                    numResult += Double.parseDouble(valueNum);
                }
            } catch (IllegalArgumentException | MissingResourceException e) {
                errorLines++;
            }
        }
        numResult = Math.round(numResult * 1000000);
        numResult /= 1000000;
        strResult.append(RESULT).append(numResult);
        return errorLines;
    }

    private static String getIndexKeyNum(String key) {
        Pattern patter = Pattern.compile("index([1-9]\\d*)");
        Matcher matcher = patter.matcher(key);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException();
    }

    private static String getIndexValue(String indexValue) {
        Pattern patter = Pattern.compile("[1-9]\\d*");
        Matcher matcher = patter.matcher(indexValue);
        if (matcher.matches()) {
            return matcher.group();
        }
        throw new IllegalArgumentException();
    }

    @Test(expected = MissingResourceException.class)
    public void testNoFile() {
        int errorLine = getResult("in9", new StringBuilder());
    }

    @Test
    public void getResultFirst() {
        final String EXP_RES1 = RESULT + "8.24";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("in1", strResult);
        Assert.assertEquals(3, errorLine);
        Assert.assertEquals(EXP_RES1, strResult.toString());
    }

    @Test
    public void getResultSecond() {
        final String EXP_RES2 = RESULT + "30.242";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("in2", strResult);
        Assert.assertEquals(9, errorLine);
        Assert.assertEquals(EXP_RES2, strResult.toString());
    }

    @Test
    public void getResultThird() {
        final String EXP_RES3 = RESULT + "1.9";
        StringBuilder strResult = new StringBuilder();
        int errorLine = getResult("in3", strResult);
        Assert.assertEquals(0, errorLine);
        Assert.assertEquals(EXP_RES3, strResult.toString());
    }
}
