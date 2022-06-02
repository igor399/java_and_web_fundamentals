package by.epam.lab.services;

import by.epam.lab.beans.Result;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultHandler {

    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private final static int NAME_IND = 0;
    private final static int DATE_IND = 1;
    private final static int MARK_IND = 2;
    private final List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public List<Result> getResults() {
        return results;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attrs) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            Result currentResult = new Result(login, attrs.getValue(String.valueOf(NAME_IND)),
                    attrs.getValue(String.valueOf(DATE_IND)), attrs.getValue(String.valueOf(MARK_IND)));
            results.add(currentResult);
        }
    }

    public void characters(char[] ch, int start, int length) {
        if (currentEnum == ResultEnum.LOGIN) {
            String str = new String(ch, start, length).trim();
            if(!str.isEmpty()){
                login = str;
            }
        }
    }
}
