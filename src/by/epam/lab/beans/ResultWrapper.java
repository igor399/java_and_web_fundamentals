package by.epam.lab.beans;

import by.epam.lab.services.MarkRepresentation;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultWrapper {
    private static MarkRepresentation markRepresentation = MarkRepresentation.INTEGER;
    private StudentData student = new StudentData();

    public ResultWrapper(String login, String test, Date date, int mark) {
        student.setLogin(login);
        student.setTestName(test);
        student.setDate(date);
        student.setMark(mark);
    }

    public ResultWrapper(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) Double.parseDouble(mark));
    }

    public ResultWrapper(StudentData student) {
        this.student = student;
    }

    public static MarkRepresentation getStringMarkMethod() {
        return markRepresentation;
    }

    public static void setStringMarkMethod(MarkRepresentation markRepresentation) {
        ResultWrapper.markRepresentation = markRepresentation;
    }

    public String getLogin() {
        return student.getLogin();
    }

    public String getTest() {
        return student.getTestName();
    }

    public Date getDate() {
        return student.getDate();
    }

    public double getMark() {
        return student.getMark();
    }

    private static String getStringDate(Date date) {
        return DATE_FORMAT.format(date);
    }

   @Override
   public String toString() {
       return getLogin() + SEMICOLON +
               getTest() + SEMICOLON +
               getStringDate(getDate()) + SEMICOLON +
               getMark();
   }
}
