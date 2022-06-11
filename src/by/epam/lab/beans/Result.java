package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class Result {
    private static RoundMethod roundMethod = RoundMethod.INTEGER;
    private String login;
    private String testName;
    private Date date;
    private int mark;

    public Result() {

    }

    public Result(String login, String testName, Date date, int mark) {
        this.login = login;
        this.testName = testName;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date),
                (int) (Double.parseDouble(mark) * CONVECTION_FACTOR));
    }

    public Result(String[] param) {
        this(param[PARAM_LOGIN_IND],
                param[PARAM_TEST_IND],
                param[PARAM_DATE_IND],
                param[PARAM_MARK_IND]);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = (int) (Double.parseDouble(mark) * CONVECTION_FACTOR);
    }

    private static String getStringDate(Date date) {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    public static void setRoundMethod(RoundMethod roundMethod) {
        Result.roundMethod = roundMethod;
    }

    @Override
    public String toString() {
        return login + SEMICOLON + testName + SEMICOLON +
                getStringDate(getDate()) + SEMICOLON + roundMethod.getMarkToString(mark);
    }
}
