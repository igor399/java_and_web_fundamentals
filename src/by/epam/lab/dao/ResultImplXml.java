package by.epam.lab.dao;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.*;
import by.epam.lab.services.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Iterator;

public class ResultImplXml implements ResultDao {
    private Iterator<Result> resultIterator;

    public ResultImplXml(String xmlDirectory, ResultFactory resultFactory)
            throws SourceException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ResultHandler handler = new ResultHandler(resultFactory);
            saxParser.parse(xmlDirectory, handler);
            resultIterator = handler.getResults().iterator();
        } catch (SAXException | ParserConfigurationException e) {
            throw new ParseRuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new SourceException(e.getMessage());
        }
    }

    @Override
    public Result nextResult() {
        return resultIterator.next();
    }

    @Override
    public boolean hasResult() {
        return resultIterator.hasNext();
    }

    @Override
    public void close() {
        resultIterator = null;
    }
}
