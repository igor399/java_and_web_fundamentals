package by.epam.lab.beans;

import by.epam.lab.services.RoundMethod;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
        this("", "", null, 0);
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), Integer.parseInt(mark));
    }

    public Result(String[] param) {
        this(param[LOGIN_INDEX],
                param[TEST_INDEX],
                param[DATE_INDEX],
                param[MARK_INDEX]);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
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
        this.mark = Integer.parseInt(mark);
    }

    protected String markToString() {
        return String.valueOf(mark);
    }

    private static String getStringDate(Date date) {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    @Override
    public String toString() {
        return getClass().getName() + SEMICOLON + login + SEMICOLON + test +
                SEMICOLON + getStringDate(getDate()) + SEMICOLON + markToString();
    }
}
