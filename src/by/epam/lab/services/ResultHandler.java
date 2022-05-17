package by.epam.lab.services;

import by.epam.lab.beans.Result;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultHandler extends DefaultHandler {

    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private Result currentResult = null;
    private String currentLogin = null;

    public ResultHandler() {

    }

    public List<Result> getResults(){
        return results;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attrs) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            currentResult = new Result();
            currentResult.setLogin(currentLogin);
            currentResult.setTest(attrs.getValue(NAME_IND));
            try {
                currentResult.setDate(Util.setDate(attrs.getValue(DATE_IND)));
            } catch (ParseException e) {
                System.out.println(PARSE_DATE_EXCEPTION + e);
            }
            currentResult.setMark(Util.setMark(attrs.getValue(MARK_IND)));
        }
    }

    public void endElement(String uri, String localName, String qName) {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            results.add(currentResult);
        }
        if (currentEnum == ResultEnum.STUDENT) {
            currentLogin = null;
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (currentEnum == ResultEnum.LOGIN && currentLogin == null) {
            currentLogin = new String(ch, start, length).trim();
        }
    }
}
