package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String DB_URL = "db.url";
    public final static String PROPERTIES_DIRECTORY = "resources/connection.properties";
    public final static String CSV1_PATH = "resources/results1.csv";
    public final static String XML_PATH = "resources/results.xml";
    public final static String CSV2_PATH = "resources/results2.csv";
    public final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat("dd.MM.yyyy");
    public final static String SEMICOLON = ";";
    public final static String COLON = ":";
    public final static String MARK_FORMAT = "%d.%d";
    public final static String MEAN_MARK_FORMAT = "%.2f";
    public final static String DECIMAL_REMAINDER = ".5";
    public final static int CONVECTION_FACTOR = 10;
    public final static int CONVECTION_HALF_FACTOR = 2;
    public final static int START_ID = 1;
    public final static int COLUMN_NAME_IND = 1;
    public final static int PARAM_LOGIN_INDEX = 0;
    public final static int PARAM_TEST_INDEX = 1;
    public final static int PARAM_DATE_INDEX = 2;
    public final static int PARAM_MARK_INDEX = 3;
    public final static int MEAN_INDEX = 2;
    public final static int LOGIN_INDEX = 1;
    public final static int TEST_INDEX = 2;
    public final static int DATE_INDEX = 3;
    public final static int MARK_INDEX = 4;
    public final static String ERR_TWICE_GET_CONNECTION_EXECUTING = "Trying get connection more then once.";
    public final static String DATA_EXCEPTION = "Wrong SQL request or something wrong with file.";
    public final static String SOURCE_EXCEPTION = "Can't open resource file.";
    public final static String LOAD_FROM_DB_EXCEPTION = "Data can not be loaded from database.";
    public final static String ERR_MSG = "An error has occurred while closing connection.";
    public final static String TITLE_MEAN_MARK = "Mean marks of students: ";
    public final static String TITLE_MONTH_RESULT = "Results of the current month: ";
    public final static String TITLE_LAST_DAY_RESULT = "Latest day results of current month: ";
}
