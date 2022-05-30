package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String SEMICOLON = ";";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/jse";
    public final static String USER = "root";
    public final static String PASS = "password";
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
    public final static int LOGIN_IND = 0;
    public final static int TEST_IND = 1;
    public final static int DATE_IND = 2;
    public final static int MARK_IND = 3;
    public final static String SAX_EXCEPTION = "SAX parser error";
    public final static String IO_EXCEPTION = "I/O thread error";
}
