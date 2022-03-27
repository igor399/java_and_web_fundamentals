import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {
    final static String NUM_REG_EXP = "[1-9]\\d*";

    private static int getResult(String fileName, StringBuilder stringResult) {
        final String KEY_REG_EXP = "index(.*)";
        final String RESULT = "sum = ";
        final String ERROR_LINE = "error-lines = ";
        double numResult = 0.0;
        int errorLines = 0;

        ResourceBundle rb = ResourceBundle.getBundle(fileName);
        Enumeration<String> keys = rb.getKeys();
        String element;
        String indexKey;
        String indexKeyNum;
        String indexValue;
        String valueNum;

        while (keys.hasMoreElements()) {
            element = keys.nextElement();
            try {
                Pattern patter = Pattern.compile(KEY_REG_EXP);
                Matcher matcher = patter.matcher(element);
                if (matcher.matches()) {
                    indexKey = matcher.group();
                    indexKeyNum = getIndexKeyNum(indexKey);
                    indexValue = getIndexValue(rb.getString(indexKey).trim());
                    valueNum = rb.getString("value" + indexKeyNum + indexValue);
                    numResult += Double.parseDouble(valueNum);
                }
            } catch (IllegalArgumentException | MissingResourceException e) {
                errorLines++;
            }
        }
        stringResult.append(RESULT).append(numResult);
        return errorLines;
    }

    private static String getIndexKeyNum(String key) {
        Pattern patter = Pattern.compile(NUM_REG_EXP);
        Matcher matcher = patter.matcher(key);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException();
    }

    private static String getIndexValue(String indexValue) {
        Pattern patter = Pattern.compile(NUM_REG_EXP);
        Matcher matcher = patter.matcher(indexValue);
        if (matcher.matches()) {
            return matcher.group();
        }
        throw new IllegalArgumentException();
    }
}