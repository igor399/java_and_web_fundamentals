package by.epam.lab.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class Result {
    private String login;
    private String test;
    private int mark;
    private Date date;

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

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private static String getValidDateFormat(Date date) {
        return new SimpleDateFormat(DATE_STRING_PATTERN).format(date);
    }

    private static String getValidMarkFormat(int mark) {
        return String.format(FORMAT_OF_MARK, mark / CONV_FACTOR, mark % CONV_FACTOR);
    }

    @Override
    public String toString() {
        return login + SEMICOLON + test + SEMICOLON + getValidDateFormat(date) +
                SEMICOLON + getValidMarkFormat(mark);
    }
}
