package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String PROPERTIES_PATH = "resources/connection.properties";
    public final static String CSV1_PATH = "resources/results1.csv";
    public final static String XML_PATH = "resources/results.xml";
    public final static String CSV2_PATH = "resources/results2.csv";
    public final static int CONVECTION_FACTOR = 10;
    public final static int START_ID = 1;
    public final static int LOGIN_INDEX = 1;
    public final static int MEAN_INDEX = 2;
    public final static int TEST_INDEX = 2;
    public final static int DATE_INDEX = 3;
    public final static int MARK_INDEX = 4;
    public final static String KEY_URL = "db.url";
    public final static String FORMAT = "%d.%d";
    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public final static String SEMICOLON = ";";
    public final static String TITLE_MEAN_MARK = "mean marks of students:";
    public final static String TITLE_MONTH_RESULT = "\nresults of the current month:";
    public final static String TITLE_LAST_DAY_RESULT = "\nlatest day results of current month:";

}
