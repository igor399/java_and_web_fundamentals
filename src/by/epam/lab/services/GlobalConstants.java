package by.epam.lab.services;

import java.text.SimpleDateFormat;

public class GlobalConstants {
    public final static String XML_PATH = "src/results.xml";
    public final static String SEMICOLON = ";";
    public final static SimpleDateFormat OUTPUT_DATE_FORMAT =
            new SimpleDateFormat("dd.MM.yyyy");
    public final static int CONV_FACTOR = 10;
    public final static int NAME_IND = 0;
    public final static int DATE_IND = 1;
    public final static int MARK_IND = 2;
    public final static String SAX_EXCEPTION = "SAX parser error";
    public final static String IO_EXCEPTION = "I/O thread error";
}
