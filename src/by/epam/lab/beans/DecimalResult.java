package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class DecimalResult extends Result {

    public DecimalResult() {
        super();
    }

    public DecimalResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public DecimalResult(String login, String test, String date, String mark) {
        super(login, test, Date.valueOf(date), castToIntMark(mark));
    }

    public DecimalResult(String[] param) {
        this(param[PARAM_LOGIN_INDEX],
                param[PARAM_TEST_INDEX],
                param[PARAM_DATE_INDEX],
                param[PARAM_MARK_INDEX]);
    }

    @Override
    public void setMark(String mark) {
        super.setMark(castToIntMark(mark));
    }

    private static int castToIntMark(String mark) {
        return (int) (Double.parseDouble(mark) * CONVECTION_FACTOR);
    }

    @Override
    protected String markToString() {
        return String.format(MARK_FORMAT, getMark() / CONVECTION_FACTOR,
                getMark() % CONVECTION_FACTOR);
    }
}
