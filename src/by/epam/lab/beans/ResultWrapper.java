package by.epam.lab.beans;

import by.epam.lab.services.MarkRepresentation;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultWrapper {
    private static MarkRepresentation markRepresentation = MarkRepresentation.INTEGER;
    private Result result = new Result();

    public ResultWrapper(String login, String test, Date date, int mark) {
        result.setLogin(login);
        result.setTestName(test);
        result.setDate(date);
        result.setMark(mark);
    }

    public ResultWrapper(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) Double.parseDouble(mark));
    }

    public ResultWrapper(Result result) {
        this.result = result;
    }

    public String getLogin() {
        return result.getLogin();
    }

    public String getTest() {
        return result.getTestName();
    }

    public Date getDate() {
        return result.getDate();
    }

    public double getMark() {
        return  result.getMark();
    }

    private static String getStringDate(Date date) {
        return OUTPUT_DATE_FORMAT.format(date);
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
