package by.epam.lab.services;

import by.epam.lab.beans.Result;
import by.epam.lab.dao.ResultDao;
import by.epam.lab.exceptions.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.services.GlobalConstants.*;
import static by.epam.lab.services.SqlRequestConstants.*;

public class RunnerLogic {
    private static final List<Result> currentMonthResults = new LinkedList<>();

    public static void execute(ResultFactory factory, String fileDirectory) {
        loadResults(factory, fileDirectory);
        printMeanMarks(factory);
        printCurrentResults(factory);
        printLastOfMathResult();
    }

    private static void printMeanMarks(ResultFactory resultFactory) {
        try (Statement st = ConnectionDbManager.CONNECTION_MANAGER
                .getConnection().createStatement();
             ResultSet rs = st.executeQuery(MEAN_MARKS)) {
            System.out.println(TITLE_MEAN_MARK);
            while (rs.next()) {
                System.out.println(rs.getString(LOGIN_INDEX) + COLON +
                        resultFactory.getStringMeanMark(rs.getDouble(MEAN_INDEX)));
            }
        } catch (SQLException e) {
            System.err.println(DATA_EXCEPTION);
        }
    }

    private static void printCurrentResults(ResultFactory resultFactory) {
        try {
            loadCurrentMonthResults(resultFactory);
            System.out.println(TITLE_MONTH_RESULT);
            for (Result result : currentMonthResults) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            System.err.println(DATA_EXCEPTION);
        }
    }

    private static void printLastOfMathResult() {
        if (currentMonthResults.size() > 0) {
            System.out.println(TITLE_LAST_DAY_RESULT);
            ListIterator<Result> it = currentMonthResults
                    .listIterator(currentMonthResults.size());
            Date date = it.previous().getDate();
            it.next();
            while (it.hasPrevious()) {
                Result res = it.previous();
                if (date.equals(res.getDate())) {
                    System.out.println(res);
                } else {
                    break;
                }
            }
        }
    }

    private static void loadCurrentMonthResults(ResultFactory resultFactory)
            throws SQLException {
        try (Statement st = ConnectionDbManager.CONNECTION_MANAGER
                .getConnection().createStatement();
             ResultSet rs = st.executeQuery(CURR_MONTH_RESULTS)) {
            while (rs.next()) {
                currentMonthResults.add(resultFactory.getResult(
                        rs.getString(LOGIN_INDEX),
                        rs.getString(TEST_INDEX),
                        rs.getDate(DATE_INDEX),
                        rs.getInt(MARK_INDEX)));
            }
        }
    }

    private static void loadResults(ResultFactory resultFactory, String fileDirectory) {
        try (ResultDao reader = resultFactory.getResultDao(fileDirectory)) {
            LoadManager.insertResult(reader);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ResourceReleaseException e) {
            System.err.println(SOURCE_EXCEPTION);
        } catch (ConnectionDbException e) {
            throw new ResourceOpeningException(LOAD_FROM_DB_EXCEPTION);
        }
    }
}
