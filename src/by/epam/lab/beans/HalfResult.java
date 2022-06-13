package by.epam.lab.beans;

import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class HalfResult extends Result {
    public HalfResult() {
        super();
    }

    public HalfResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public HalfResult(String login, String test, String date, String mark) {
        super(login, test, java.sql.Date.valueOf(date),
                (int) (Double.parseDouble(mark) * 2);
    }

    public HalfResult(String[] param) {
        this(param[LOGIN_INDEX],
                param[TEST_INDEX],
                param[DATE_INDEX],
                param[MARK_INDEX]);
    }

    @Override
    public void setMark(String mark) {
        super.setMark((int) (Double.parseDouble(mark) * 2));
    }

    @Override
    protected String markToString() {
        String result;
        int mark = getMark();
        if (mark % CONVECTION_FACTOR == 0) {
            result = String.valueOf(mark / CONVECTION_FACTOR);
        } else {
            result = String.format(MARK_FORMAT, mark /
                    CONVECTION_FACTOR, mark % CONVECTION_FACTOR);
        }
        return result;
    }
}
