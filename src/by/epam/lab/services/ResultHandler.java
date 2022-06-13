package by.epam.lab.services;

import by.epam.lab.beans.Result;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.services.GlobalConstants.*;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private final List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public ResultHandler() {

    }

    public List<Result> getResults() {
        return results;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentEnum = ResultEnum.valueOf(qName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            Result current = new Result(login,
                    attributes.getValue(LOGIN_INDEX),
                    attributes.getValue(TEST_INDEX),
                    attributes.getValue(DATE_INDEX));
            results.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentEnum == ResultEnum.LOGIN) {
            String str = new String(ch, start, length).trim();
            if (!str.isEmpty()) {
                login = str;
            }
        }
    }
}
