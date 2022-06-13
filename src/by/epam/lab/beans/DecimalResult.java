package by.epam.lab.beans;

import com.mysql.cj.log.Log;

import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class DecimalResult extends Result {
    public DecimalResult() {
        super();
    }

    public DecimalResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public DecimalResult(String login, String test, String date, String mark) {
        super(login, test, java.sql.Date.valueOf(date),
                (int) (Double.parseDouble(mark) * CONVECTION_FACTOR);;
    }

    public DecimalResult(String[] param) {
        this(param[LOGIN_INDEX],
                param[TEST_INDEX],
                param[DATE_INDEX],
                param[MARK_INDEX]);
    }

    @Override
    public void setMark(String mark) {
        super.setMark((int) (Double.parseDouble(mark) * CONVECTION_FACTOR));
    }

    @Override
    protected String markToString() {
        return String.format(MARK_FORMAT, getMark() / CONVECTION_FACTOR,
                getMark() % CONVECTION_FACTOR);
    }
}
