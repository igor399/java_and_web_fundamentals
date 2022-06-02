package by.epam.lab.beans;

import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result(String string, String curMonthSetString, java.sql.Date date, int anInt) {
        this("", "", null, "");
    }

    public Result(String login, String test, String date, String mark) {
        this.login = login;
        this.test = test;
        this.date = java.sql.Date.valueOf(date);
        this.mark = (int) (Double.parseDouble(mark) * CONV_FACTOR);
    }

    public Result(String[]param){
        this(param[LOGIN_IND], param[TEST_IND], param[DATE_IND], param[MARK_IND]);
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

    public void setMark(int mark) {
        this.mark = mark;
    }

    private static String getStringDate(Date date) {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    private static String getStringMark(int mark) {
        return mark / CONV_FACTOR + "." + mark % CONV_FACTOR;
    }

    @Override
    public String toString() {
        return login + SEMICOLON + test + SEMICOLON + getStringDate(date) +
                SEMICOLON + getStringMark(mark);
    }
}
