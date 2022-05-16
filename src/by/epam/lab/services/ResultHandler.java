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

    private List<Result> results = new ArrayList<Result>();
    private ResultEnum currentEnum;
	private Result currentResult = null;
	private String currentLogin = null;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            currentResult = new Result();
            currentResult.setLogin(currentLogin);
            currentResult.setTest(attributes.getValue(NAME_IND));






        }
    }

    public void characters(char[] ch, int start, int length) {
        if (currentEnum == ResultEnum.LOGIN) {
            //save the login
			...
        }
    }
}
