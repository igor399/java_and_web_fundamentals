package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String XML_PATH = "src/results.xml";
    public final static String SEMICOLON = ";";
    public final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat("dd.MM.yyyy");
    public final static int CONV_FACTOR = 10;
    public final static int NAME_IND = 0;
    public final static int DATE_IND = 1;
    public final static int MARK_IND = 2;
    public final static String SAX_EXCEPTION = "SAX parser error";
    public final static String IO_EXCEPTION = "I/O thread error";


    private static final String INSERT_RESULT = "INSERT INTO results VALUES(?, ?, ?, ?)";

    private static final String INSERT_LOGIN = "INSERT INTO logins (name) VALUES (?)";

    private static final String INSERT_TEST = "INSERT INTO tests (name) VALUES (?)";

    private static final String GET_MEAN_MARKS = "SELECT logins.name, AVG(mark) FROM logins, results WHERE results.loginId = logins.idLogin GROUP BY loginId";

    private static final String GET_CURR_MONTH_RESULTS = "SELECT logins.name, tests.name, results.dat, results.mark FROM logins, tests, results " +
                    "WHERE results.loginId = logins.idLogin AND results.testId = tests.idTest AND " +
                    "YEAR(results.dat) = YEAR(CURRENT_DATE()) AND " +
                    "MONTH(results.dat) = MONTH(CURRENT_DATE()) ORDER BY results.dat";
}
