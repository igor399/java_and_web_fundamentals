package by.epam.lab.dal;

import by.epam.lab.beans.ResultWrapper;
import by.epam.lab.services.MarkRepresentation;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

import static by.epam.lab.services.GlobalConstants.*;
import static by.epam.lab.services.SqlRequestConstants.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public abstract class ResultManager  {
    private final LinkedList<ResultWrapper> currentMonthResults = new LinkedList<>();
    protected String dbUrl;
    protected Properties properties;

    public ResultManager(String propertiesPath, MarkRepresentation markRepresentation) throws IOException {
        properties = new Properties();
        properties.load(new FileReader(propertiesPath));
        dbUrl = properties.getProperty(KEY_URL);
        ResultWrapper.setStringMarkRepresentation(markRepresentation);
    }

    protected static void insertResult(Connection cn, ResultWrapper result) throws SQLException {
        int loginId;
        try {
            PreparedStatement insertLogin = cn.prepareStatement(INSERT_LOGIN, RETURN_GENERATED_KEYS);
            insertLogin.setString(1, result.getLogin());
            insertLogin.executeUpdate();
            loginId = getInsertedId(insertLogin);
        } catch (SQLIntegrityConstraintViolationException e) {
            ResultSet resultSet = cn.prepareStatement(SELECT_ID_LOGIN + result.getLogin() + "'").executeQuery();
            resultSet.next();
            loginId = resultSet.getInt(START_ID);
        }
        int testId;
        try {
            PreparedStatement insertTest = cn.prepareStatement(INSERT_TEST, RETURN_GENERATED_KEYS);
            insertTest.setString(1, result.getTest());
            insertTest.executeUpdate();
            testId = getInsertedId(insertTest);
        } catch (SQLIntegrityConstraintViolationException e) {
            ResultSet resultSet = cn.prepareStatement(SELECT_ID_TEST + result.getTest() + "'").executeQuery();
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
             ResultSet meanMarksSet = st.executeQuery(MEAN_MARKS)) {
            System.out.println(TITLE_MEAN_MARK);
            while (meanMarksSet.next()) {
                System.out.printf("%s : %.2f\n", meanMarksSet.getString(LOGIN_INDEX),
                        meanMarksSet.getDouble(MEAN_INDEX));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printCurrentResult() {
        try {
            loadCurrentMonthResult();
            System.out.println(TITLE_MONTH_RESULT);
            for (ResultWrapper result : currentMonthResults) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printLastOfMonthResult() {
        if (currentMonthResults.size() > 0) {
            System.out.println(TITLE_LAST_DAY_RESULT);
            LinkedList<ResultWrapper> tmpResults = new LinkedList<>(currentMonthResults);
            Date date = tmpResults.getLast().getDate();
            while (tmpResults.getLast().getDate().equals(date)) {
                System.out.println(tmpResults.pollLast());
            }
        }
    }

    private void loadCurrentMonthResult() throws SQLException {
        try (Connection cn = DriverManager.getConnection(dbUrl, properties);
             Statement st = cn.createStatement();
             ResultSet currentMonthSet = st.executeQuery(CURR_MONTH_RESULTS)) {
            while (currentMonthSet.next()) {
                currentMonthResults.add(new ResultWrapper(
                        currentMonthSet.getString(LOGIN_INDEX),
                        currentMonthSet.getString(TEST_INDEX),
                        currentMonthSet.getDate(DATE_INDEX),
                        currentMonthSet.getInt(MARK_INDEX)));
            }
        }
    }
}
