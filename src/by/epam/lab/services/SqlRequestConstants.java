package by.epam.lab.services;

public final class SqlRequestConstants {
    public static final String INSERT_RESULT = "INSERT INTO results (loginId, " +
            "testId, dat, mark) VALUES(?, ?, ?, ?)";
    public static final String INSERT_LOGIN = "INSERT INTO logins (name) VALUES (?)";
    public static final String INSERT_TEST = "INSERT INTO tests (name) VALUES (?)";
    public static final String SELECT_ID_LOGIN = "SELECT idLogin FROM logins " +
            "WHERE name like '";

    public static final String SELECT_ID_TEST = "SELECT idTest FROM tests " +
            "WHERE name like '";

    public static final String MEAN_MARKS = "SELECT logins.name, AVG(results.mark) " +
            "FROM logins, results WHERE results.loginId = logins.idLogin GROUP " +
            "BY logins.idLogin";

    public static final String CURR_MONTH_RESULTS = "SELECT logins.name, tests.name, " +
            "results.dat, results.mark FROM logins, tests, results WHERE " +
            "results.loginId = logins.idLogin AND results.testId = tests.idTest " +
            "AND MONTH(results.dat) = MONTH(CURRENT_DATE()) ORDER BY results.dat";
}
