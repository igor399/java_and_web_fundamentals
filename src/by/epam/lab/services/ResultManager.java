package by.epam.lab.services;

import by.epam.lab.beans.Result;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultManager {
    private final static int LOGIN_IND = 1;
    private final static int TEST_IND = 2;
    private final static int DATE_IND = 3;
    private final static int MARK_IND = 4;
    private final static int MEAN_IND = 2;
    protected String dbUrl;
    protected Properties properties;
    private final List<Result> currentMonthRes = new ArrayList<>();

    public ResultManager(String propPath) throws IOException {
        properties = new Properties();
        properties.load(new FileReader(propPath));
        dbUrl = properties.getProperty(DB_URL);
    }

    public void printStudentMeanMarks() {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = cn.createStatement();

             ResultSet meanMarks = st.executeQuery(GET_MEAN_MARKS)) {
            while (meanMarks.next()) {
                System.out.println(meanMarks);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void printLastDayRes() {
        if (currentMonthRes.size() > 0) {
            LinkedList<Result> tmpRes = new LinkedList<>(currentMonthRes);
            Date date = tmpRes.getLast().getDate();
            while (tmpRes.getLast().getDate().equals(date)) {
                System.out.println(tmpRes.pollLast());
            }
        }
    }

    public void printCurrMonthRes() {
        try {
            currentMonthRes.clear();
            loadCurrentMonthRes();
            for (Result result : currentMonthRes) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void loadCurrentMonthRes() throws SQLException {
        try (Connection cn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement st = cn.createStatement();
             ResultSet curMonthSet = st.executeQuery(GET_CURR_MONTH_RESULTS)) {
            while (curMonthSet.next()) {
                currentMonthRes.add(new Result(
                        curMonthSet.getString(LOGIN_IND),
                        curMonthSet.getString(TEST_IND),
                        curMonthSet.getDate(DATE_IND),
                        curMonthSet.getInt(MARK_IND)));
            }
        }
    }

    protected static void insertRes(Connection cn, Result result) throws SQLException {
        try (PreparedStatement inLogin = cn.prepareStatement(INSERT_LOGIN);
             PreparedStatement inTest = cn.prepareStatement(INSERT_TEST);
             PreparedStatement inResult = cn.prepareStatement(INSERT_RESULT)) {
            inLogin.setString(1, result.getLogin());
            inLogin.setString(2, result.getLogin());
            inLogin.executeUpdate();
            inTest.setString(1, result.getTest());
            inTest.setString(2, result.getTest());
            inTest.executeUpdate();
            inResult.setString(LOGIN_IND, result.getLogin());
            inResult.setString(TEST_IND, result.getTest());
            inResult.setString(DATE_IND, String.valueOf(result.getDate()));
            inResult.setInt(MARK_IND, result.getMark());
            inResult.executeUpdate();
        }
    }
}
