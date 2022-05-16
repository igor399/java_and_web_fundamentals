package by.epam.lab.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.epam.lab.services.GlobalConstants.*;

public class Util {

    public static Date setDate(String date)throws ParseException{
        return new SimpleDateFormat(DATE_XML_PATTERN).parse(date);
    }

    public static int setMark(String mark){
        double markDouble = Double.parseDouble(mark);
        return (int) (markDouble * CONV_FACTOR);
    }
}
