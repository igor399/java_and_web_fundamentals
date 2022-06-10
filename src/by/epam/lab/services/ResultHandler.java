package by.epam.lab.services;

import by.epam.lab.beans.ResultWrapper;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private static final int NAME_IND = 0;
    private static final int DATE_IND = 1;
    private static final int MARK_IND = 2;
    private final List<ResultWrapper> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;

    public ResultHandler() {
    }

    public List<ResultWrapper> getResults() {
        return results;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentEnum = ResultEnum.valueOf(qName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            ResultWrapper current = new ResultWrapper(login,
                    attributes.getValue(NAME_IND),
                    attributes.getValue(DATE_IND),
                    attributes.getValue(MARK_IND));
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