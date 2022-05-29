package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String SEMICOLON = ";";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/jse";
    public final static String USER = "jse";
    public final static String PASS = "root";
    private static final String INSERT_RESULT = "INSERT INTO results VALUES(" +
            "(SELECT idLogin FROM logins WHERE name = ?)," +
            "(SELECT idTest FROM tests WHERE name = ?)," +
            "(?)," +
            "(?))";
    private static final String INSERT_LOGIN =
            "INSERT INTO logins (name) SELECT ? WHERE NOT EXISTS (SELECT name FROM logins WHERE name = ?)";
    private static final String INSERT_TEST =
            "INSERT INTO tests (name) SELECT ? WHERE NOT EXISTS (SELECT name FROM tests WHERE name = ?)";
    private static final String GET_MEAN_MARKS =
            "SELECT logins.name, AVG(mark) FROM logins, results WHERE results.loginId = logins.idLogin GROUP BY loginId";
    private static final String GET_CURR_MONTH_RESULTS =
            "SELECT logins.name, tests.name, results.dat, results.mark FROM logins, tests, results " +
                    "WHERE results.loginId = logins.idLogin AND results.testId = tests.idTest AND " +
                    "YEAR(results.dat) = YEAR(CURRENT_DATE()) AND " +
                    "MONTH(results.dat) = MONTH(CURRENT_DATE()) ORDER BY results.dat";






    public final static String XML_PATH = "src/results.xml";
    public final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat("dd.MM.yyyy");
    public final static int CONV_FACTOR = 10;
    private static final int LOGIN_IND = 1;
    private static final int TEST_IND = 2;
    private static final int DATE_IND = 3;
    private static final int MARK_IND = 4;
    private static final int MEAN_IND = 2;

    public final static String SAX_EXCEPTION = "SAX parser error";
    public final static String IO_EXCEPTION = "I/O thread error";
}
