package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String DB_URL = "db.url";

    public final static String PROPERTIES_PATH = "resources/connection.properties";
    public final static String CSV1_PATH = "resources/results1.csv";
    public final static String XML_PATH = "resources/results.xml";
    public final static String CSV2_PATH = "resources/results2.csv";
    public final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public final static String MARK_FORMAT = "%d.%d";
    public final static String MEAN_MARK_FORMAT = "%.2f";
    public final static String SEMICOLON = ";";
    public final static String COLON = ":";
    public final static String TITLE_MEAN_MARK = "mean marks of students:";
    public final static String TITLE_MONTH_RESULT = "\nresults of the current month:";
    public final static String TITLE_LAST_DAY_RESULT = "\nlatest day results of current month:";
    public final static int START_ID = 1;
    public final static int CONVECTION_FACTOR = 10;
    public final static int LOGIN_INDEX = 0;
    public final static int TEST_INDEX = 1;
    public final static int DATE_INDEX = 2;
    public final static int MEAN_INDEX = 2;
    public final static int MARK_INDEX = 3;
}
