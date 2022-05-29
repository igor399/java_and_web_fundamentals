package by.epam.lab.services;

import by.epam.lab.beans.Result;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultHandler extends DefaultHandler {

    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login = null;

    public List<Result> getResults() {
        return results;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attrs) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            Result currentResult = new Result(login, attrs.getValue(NAME_IND),
                    attrs.getValue(DATE_IND), attrs.getValue(MARK_IND));
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
