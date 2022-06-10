package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class StudentData {
    private String login;
    private String testName;
    private Date date;
    private int mark;

    public StudentData() {

    }

    public StudentData(String login, String testName, Date date, int mark) {
        this.login = login;
        this.testName = testName;
        this.date = date;
        this.mark = mark;
    }

    public String getInteger() {
        return String.valueOf(mark / CONVECTION_FACTOR);
    }

    public String getDecimal() {
        return String.format(PATTERN_FORMAT, mark / CONVECTION_FACTOR,
                mark % CONVECTION_FACTOR);
    }

    public String getDecimalHalf() {
        String result;
        if (mark % CONVECTION_FACTOR == 0) {
            result = String.valueOf(mark / CONVECTION_FACTOR);
        } else {
            result = String.format(PATTERN_FORMAT, mark / CONVECTION_FACTOR,
                    mark % CONVECTION_FACTOR);
        }
        return result;
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
