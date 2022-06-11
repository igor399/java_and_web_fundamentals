package by.epam.lab.dal;

import by.epam.lab.beans.Result;
import by.epam.lab.services.RoundMethod;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

import static by.epam.lab.services.GlobalConstants.*;
import static by.epam.lab.services.SqlRequestConstants.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public abstract class ResultManager {
    private final LinkedList<Result> currentMonthResults = new LinkedList<>();
    protected String dbUrl;
    protected Properties properties;

    public ResultManager(String propertiesPath, RoundMethod roundMethod) throws IOException {
        properties = new Properties();
        properties.load(new FileReader(propertiesPath));
        dbUrl = properties.getProperty(DB_URL);
        Result.setRoundMethod(roundMethod);
    }

    protected static void insertResult(Connection cn, Result result) throws SQLException {
        int loginId;
        try {
            PreparedStatement insertLogin = cn.prepareStatement(INSERT_LOGIN,
                    RETURN_GENERATED_KEYS);
            insertLogin.setString(START_ID, result.getLogin());
            insertLogin.executeUpdate();
            loginId = getInsertedId(insertLogin);
        } catch (SQLIntegrityConstraintViolationException e) {
            ResultSet resultSet = cn.prepareStatement(SELECT_ID_LOGIN +
                    result.getLogin() + "'").executeQuery();
            resultSet.next();
            loginId = resultSet.getInt(START_ID);
        }
        int testId;
        try {
            PreparedStatement insertTest = cn.prepareStatement(INSERT_TEST,
                    RETURN_GENERATED_KEYS);
            insertTest.setString(START_ID, result.getTestName());
            insertTest.executeUpdate();
            testId = getInsertedId(insertTest);
        } catch (SQLIntegrityConstraintViolationException e) {
            ResultSet resultSet = cn.prepareStatement(SELECT_ID_TEST +
                    result.getTestName() + "'").executeQuery();
            resultSet.next();
            testId = resultSet.getInt(START_ID);
        }
        PreparedStatement insertResult = cn.prepareStatement(INSERT_RESULT);
        insertResult.setInt(LOGIN_INDEX, loginId);
        insertResult.setInt(TEST_INDEX, testId);
        insertResult.setDate(DATE_INDEX, result.getDate());
        insertResult.setDouble(MARK_INDEX, result.getMark());
        insertResult.executeUpdate();
    }

    private static int getInsertedId(PreparedStatement statement) {
        try {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(START_ID);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMeanMarks() {
        try (Connection cn = DriverManager.getConnection(dbUrl, properties);
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(MEAN_MARKS)) {
            System.out.println(TITLE_MEAN_MARK);
            while (rs.next()) {
                System.out.println(rs.getString(LOGIN_INDEX) + ":" +
                        String.format(MEAN_MARK_FORMAT,
                                rs.getDouble(MEAN_INDEX) / CONVECTION_FACTOR));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printCurrentResult() {
        try {
            loadCurrentMonthResult();
            System.out.println(TITLE_MONTH_RESULT);
            for (Result result : currentMonthResults) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printLastOfMonthResult() {
        if (currentMonthResults.size() > 0) {
            System.out.println(TITLE_LAST_DAY_RESULT);
            LinkedList<Result> tmpRes = new LinkedList<>(currentMonthResults);
            Date date = tmpRes.getLast().getDate();
            while (tmpRes.getLast().getDate().equals(date)) {
                System.out.println(tmpRes.pollLast());
            }
        }
    }

    private void loadCurrentMonthResult() throws SQLException {
        try (Connection cn = DriverManager.getConnection(dbUrl, properties);
             Statement st = cn.createStatement();
             ResultSet currentMonthSet = st.executeQuery(CURR_MONTH_RESULTS)) {
            while (currentMonthSet.next()) {
                currentMonthResults.add(new Result(
                        currentMonthSet.getString(LOGIN_INDEX),
                        currentMonthSet.getString(TEST_INDEX),
                        currentMonthSet.getDate(DATE_INDEX),
                        currentMonthSet.getInt(MARK_INDEX)));
            }
        }
    }
}
