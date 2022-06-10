package by.epam.lab.beans;

import by.epam.lab.services.MarkRepresentation;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultWrapper {
    private static MarkRepresentation markRepresentation = MarkRepresentation.INTEGER;
    private StudentData studentData = new StudentData();

    public ResultWrapper(String login, String test, Date date, int mark) {
        studentData.setLogin(login);
        studentData.setTestName(test);
        studentData.setDate(date);
        studentData.setMark(mark);
    }

    public ResultWrapper(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) Double.parseDouble(mark));
    }

    public ResultWrapper(StudentData studentData) {
        this.studentData = studentData;
    }

    public String getLogin() {
        return studentData.getLogin();
    }

    public String getTest() {
        return studentData.getTestName();
    }

    public Date getDate() {
        return studentData.getDate();
    }

    public double getMark() {
        return studentData.getMark();
    }

    private static String getStringDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static MarkRepresentation getStringMarkRepresentation() {
        return markRepresentation;
    }

    public static void setStringMarkRepresentation(MarkRepresentation markRepresentation) {
        ResultWrapper.markRepresentation = markRepresentation;
    }

   @Override
   public String toString() {
       return getLogin() + SEMICOLON +
               getTest() + SEMICOLON +
               getStringDate(getDate()) + SEMICOLON +
               getMark();
   }
}