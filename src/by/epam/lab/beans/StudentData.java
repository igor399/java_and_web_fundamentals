package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class StudentData {
    private String login;
    private String testName;
    private Date date;
    private double mark;

    public StudentData() {
    }

    public StudentData(String login, String testName, Date date, double mark) {
        this.login = login;
        this.testName = testName;
        this.date = date;
        this.mark = mark;
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

    public double getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
