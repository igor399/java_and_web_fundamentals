package by.epam.lab.services;

import by.epam.lab.beans.DecimalResult;
import by.epam.lab.beans.HalfResult;
import by.epam.lab.beans.Result;
import by.epam.lab.dao.ResultDao;
import by.epam.lab.dao.ResultImplCsv;
import by.epam.lab.dao.ResultImplXml;
import by.epam.lab.exceptions.SourceException;

import java.sql.Date;

import static by.epam.lab.services.GlobalConstants.*;

public enum ResultFactory {
    INTEGER {
        @Override
        public Result getResult(String login, String test, Date date, int mark) {
            return new Result(login, test, date, mark);
        }

        @Override
        public Result getResult(String login, String test, String date, String mark) {
            return new Result(login, test, date, mark);
        }

        @Override
        public Result getResult(String[] param) {
            return new Result(param);
        }

        @Override
        public String getStringMeanMark(double meanMark) {
            return String.format(MEAN_MARK_FORMAT, meanMark);
        }

        @Override
        public ResultDao getResultDao(String fileDirectory) throws SourceException {
            return new ResultImplCsv(fileDirectory, INTEGER);
        }
    },
    DECIMAL {
        @Override
        public Result getResult(String login, String test, Date date, int mark) {
            return new DecimalResult(login, test, date, mark);
        }

        @Override
        public Result getResult(String login, String test, String date, String mark) {
            return new DecimalResult(login, test, date, mark);
        }

        @Override
        public Result getResult(String[] param) {
            return new DecimalResult(param);
        }

        @Override
        public String getStringMeanMark(double meanMark) {
            return String.format(MEAN_MARK_FORMAT, meanMark / CONVECTION_FACTOR);
        }

        @Override
        public ResultDao getResultDao(String fileDirectory) throws SourceException {
            return new ResultImplXml(fileDirectory, DECIMAL);
        }
    },
    DECIMAL_HALF {
        @Override
        public Result getResult(String login, String test, Date date, int mark) {
            return new HalfResult(login, test, date, mark);
        }

        @Override
        public Result getResult(String login, String test, String date, String mark) {
            return new HalfResult(login, test, date, mark);
        }

        @Override
        public Result getResult(String[] param) {
            return new HalfResult(param);
        }

        @Override
        public String getStringMeanMark(double meanMark) {
            return String.format(MEAN_MARK_FORMAT, meanMark / CONVECTION_HALF_FACTOR);
        }

        @Override
        public ResultDao getResultDao(String fileDirectory) throws SourceException {
            return new ResultImplCsv(fileDirectory, DECIMAL_HALF);
        }
    };

    public abstract Result getResult(String login, String test, Date date, int mark);

    public abstract Result getResult(String login, String test, String date, String mark);

    public abstract Result getResult(String[] params);

    public abstract String getStringMeanMark(double meanMark);

    public abstract ResultDao getResultDao(String fileDirectory)
            throws SourceException;
}
