package by.epam.lab.beans;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class HalfResult extends Result {

    public HalfResult() {
        super();
    }

    public HalfResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public HalfResult(String login, String test, String date, String mark) {
        super(login, test, Date.valueOf(date), castToIntMark(mark));
    }

    public HalfResult(String[] param) {
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
        return (int) (Double.parseDouble(mark) * CONVECTION_HALF_FACTOR);
    }

    @Override
    protected String markToString() {
        int mark = getMark();
        return (mark >> 1) + ((mark & 1) == 0 ? SPACE : DECIMAL_REMAINDER);
    }
}
